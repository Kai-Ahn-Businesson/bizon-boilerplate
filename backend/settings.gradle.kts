rootProject.name = "backend"

pluginManagement {
    // 버전 변수 정의
    val springBootVersion = providers.gradleProperty("springBootVersion").orNull ?: "3.5.5"
    val hibernatePluginVersion = providers.gradleProperty("hibernateVersion").orNull ?: "6.6.26.Final"
    val kotlinVersion = providers.gradleProperty("kotlinVersion").orNull ?: "2.2.10"
    val dependencyManageVer = providers.gradleProperty("dependencyManageVer").orNull ?: "1.1.7"

    repositories {}
    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        kotlin("plugin.jpa") version kotlinVersion
        kotlin("kapt") version kotlinVersion //TODO: kapt maintanance 전환, 이후 ksp로 교체 plan
        id("com.google.devtools.ksp") version "$kotlinVersion-2.0.2"
        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version dependencyManageVer
        id("org.hibernate.orm") version hibernatePluginVersion
        id("org.graalvm.buildtools.native") version "0.10.6"
    }
}
// 멀티 모듈 자를 때
//include("backend")
//include("common")
//include("api")