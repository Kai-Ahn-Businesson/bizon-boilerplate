import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    kotlin("jvm") version "2.2.10"
    kotlin("plugin.spring") version "2.2.10"
    kotlin("plugin.jpa") version "2.2.10"
    kotlin("kapt") version "2.2.10" //TODO: kapt는 유지보수로 바뀌었으므로 최대한 ksp로 전부 교체할 것
    id("com.google.devtools.ksp") version "2.2.10-2.0.2"
    id("org.springframework.boot") version "3.5.5"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.hibernate.orm") version "6.6.26.Final"
    id("org.graalvm.buildtools.native") version "0.10.6"


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


    // Configuration Processor
//    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    // TODO: 아직 해당 부분 스프링 부트가 미지원 하는 듯, 일단 레거시로 대체..
    //  ksp("org.springframework.boot:spring-boot-configuration-processor")
    kapt("org.springframework.boot:spring-boot-configuration-processor")


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