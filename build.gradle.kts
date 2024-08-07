import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

plugins {
    application
    id("java")
    id("io.spring.dependency-management") version "1.1.6"
}

buildscript {
    repositories {
        maven {
            url = uri("https://repo.spring.io/plugins-snapshot")
        }
    }
    dependencies {
        classpath("io.spring.gradle:dependency-management-plugin:<<snapshot-version>>")
    }
}

configure(allprojects) {
    group = "org.example"
    version = "1.0-SNAPSHOT"

    tasks.withType(Test::class) {
        useJUnitPlatform() {

        }
    }
}

configure(subprojects) {
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")

    tasks.withType(JavaCompile::class) {
        sourceCompatibility = "${JavaVersion.VERSION_21}"
        targetCompatibility = "${JavaVersion.VERSION_21}"
    }

    configure<DependencyManagementExtension> {
        dependencies {
            dependency("com.microsoft.playwright:playwright:1.45.1")

            dependency("org.junit.jupiter:junit-jupiter-engine:5.10.2")
            dependency("org.junit.jupiter:junit-jupiter-api:5.10.2")
            dependency("org.junit.jupiter:junit-jupiter-params:5.10.2")
        }
    }
}

tasks.register<JavaExec>("playwright") {
    classpath(sourceSets["test"].runtimeClasspath)
    mainClass.set("com.microsoft.playwright.CLI")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

repositories {
    mavenLocal()
    mavenCentral()
}

