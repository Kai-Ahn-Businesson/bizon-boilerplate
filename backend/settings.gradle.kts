rootProject.name = "backend"

pluginManagement {
    // 버전 변수 정의
    val springBootVersion = "3.5.5"
    val kotlinVersion = "2.2.10"
    val dependencyManagementVersion = "1.1.7"
    repositories {}
    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        kotlin("plugin.jpa") version kotlinVersion
        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version dependencyManagementVersion
    }
}
// 멀티 모듈 자를 때
//include("backend")
//include("common")
//include("api")