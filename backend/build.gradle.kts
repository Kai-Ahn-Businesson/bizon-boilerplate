import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    kotlin("kapt")
    id("com.google.devtools.ksp")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("org.hibernate.orm")
    id("org.graalvm.buildtools.native")
    id("org.owasp.dependencycheck") version "9.0.9"
}

group = "com.bizon"
var releaseVer = "v0.0.1"
version =
    "$releaseVer-${LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))}"
description = "backend"

java {
    toolchain { languageVersion = JavaLanguageVersion.of(24) }
    sourceCompatibility = JavaVersion.VERSION_24
}
kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict", "-opt-in=kotlin.RequiresOptIn")
        allWarningsAsErrors = true
    }
}

configurations {
    compileOnly { extendsFrom(configurations.annotationProcessor.get()) }
    testCompileOnly { extendsFrom(configurations.testAnnotationProcessor.get()) }
}
repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-batch")
    implementation("org.springframework.boot:spring-boot-starter-hateoas")
    implementation("org.springframework.boot:spring-boot-starter-integration")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.springframework.data:spring-data-rest-hal-explorer")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.tngtech.archunit:archunit-junit5-engine:1.4.1")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // dev only
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    //DB
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")    // 프로덕션
    runtimeOnly("com.h2database:h2")            // 개발 환경: H2 사용 (빠른 개발/디버깅)
    testImplementation("com.h2database:h2")     // 테스트: H2 사용 (격리된 테스트 환경) - `@DataJpaTest`등


    // Configuration Processor : 아직 ksp 없음
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    // mapstruct
    implementation("org.mapstruct:mapstruct:1.6.3")
    testImplementation("org.mapstruct:mapstruct:1.6.3")
    kapt("org.mapstruct:mapstruct-processor:1.6.3")
    kaptTest("org.mapstruct:mapstruct-processor:1.6.3")

}


//  kotlin
tasks.withType<KotlinCompile> {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_24)
        languageVersion.set(KotlinVersion.KOTLIN_2_2)
        apiVersion.set(KotlinVersion.KOTLIN_2_2)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

graalvmNative {
    binaries {
        configureEach {
            verbose = true
            buildArgs.add("-H:-CheckToolchain")
        }
    }
}

// OWASP Dependency Check 설정
dependencyCheck {
    // CVE 데이터베이스 자동 업데이트 비활성화 (CI/CD에서 시간 절약)
    autoUpdate = false
    
    // 분석할 형식 지정
    analyzers.apply {
        assemblyEnabled = false     // .NET assembly 분석 비활성화
        jarEnabled = true          // JAR 파일 분석 활성화
        centralEnabled = true      // Maven Central 분석 활성화
    }
    
    // 보고서 형식 설정
    formats = listOf("HTML", "XML")
    
    // 심각도 임계값 설정 (CVSS 점수 7.0 이상을 고위험으로 분류)
    failBuildOnCVSS = 7.0f
    
    // 취약점 억제 설정 (필요시)
    // suppressionFile = "owasp-suppressions.xml"
}