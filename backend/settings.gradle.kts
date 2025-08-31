rootProject.name = "backend"

pluginManagement {
    // 버전 변수 정의
    val springBootVersion = "3.5.5"
    val kotlinVersion = "2.2.10"
    val dependencyManagementVersion = "1.1.7"
    val hibernatePluginVersion ="6.6.26.Final"
    repositories {}
    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        kotlin("plugin.jpa") version kotlinVersion
        kotlin("kapt") version kotlinVersion //TODO: kapt는 유지보수로 바뀌었으므로 최대한 ksp로 전부 교체할 것
        id("com.google.devtools.ksp") version "2.2.10-2.0.2"
        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version dependencyManagementVersion
        id("org.hibernate.orm") version hibernatePluginVersion
        id("org.graalvm.buildtools.native") version "0.10.6"
    }
}
// 멀티 모듈 자를 때
//include("backend")
//include("common")
//include("api")