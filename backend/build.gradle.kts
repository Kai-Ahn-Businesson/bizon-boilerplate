import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    kotlin("jvm") version "2.2.0"
    kotlin("plugin.spring") version "2.2.0"
    id("org.springframework.boot") version "3.5.5"
    id("io.spring.dependency-management") version "1.1.7"
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
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}


//  kotlin
tasks.withType<KotlinCompile> {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_24)
        languageVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_2)
        apiVersion.set(KotlinVersion.KOTLIN_2_2)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
