import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

plugins {
    application
    id("java")
    id("io.qameta.allure") version "2.12.0"
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
    group = "org.demo"
    version = "1.0-SNAPSHOT"

    tasks.withType(Test::class) {
        useJUnitPlatform() {

        }
    }
}

configure(subprojects) {
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "io.qameta.allure")

    tasks.withType(JavaCompile::class) {
        sourceCompatibility = "${JavaVersion.VERSION_17}"
        targetCompatibility = "${JavaVersion.VERSION_17}"
        options.encoding = "UTF-8"
        options.compilerArgs.add("-parameters")
    }

    configure<DependencyManagementExtension> {
        dependencies {
            dependency("com.microsoft.playwright:playwright:1.45.1")

            dependency("com.codeborne:selenide:7.5.1")

            dependency("org.slf4j:slf4j-api:2.0.16")
            dependency("org.slf4j:slf4j-simple:2.0.16")

            dependency("io.qameta.allure:allure-okhttp3:2.29.0")
            dependency("io.qameta.allure:allure-java-commons:2.29.0")

            dependency("com.github.javafaker:javafaker:1.0.2")
            dependency("org.awaitility:awaitility:4.2.2")

            dependency("org.projectlombok:lombok:1.18.34")

            dependency("com.google.inject:guice:7.0.0")
            dependency("name.falgout.jeffrey.testing.junit5:guice-extension:1.2.1")
            dependency("org.aeonbits.owner:owner:1.0.12")

            dependency("org.jdbi:jdbi3-core:3.45.4")
            dependency("org.jdbi:jdbi3-sqlobject:3.45.4")
            dependency("org.jdbi:jdbi3-postgres:3.45.4")

            dependency("com.squareup.retrofit2:retrofit:2.11.0")
            dependency("com.squareup.retrofit2:converter-jackson:2.11.0")
            dependency("com.fasterxml.jackson.core:jackson-databind:2.18.0")
            dependency("com.fasterxml.jackson.module:jackson-module-parameter-names:2.18.0")
            dependency("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.18.0")

            dependency("org.junit.jupiter:junit-jupiter-engine:5.10.2")
            dependency("org.junit.jupiter:junit-jupiter-api:5.10.2")
            dependency("org.junit.jupiter:junit-jupiter-params:5.10.2")

            dependency("org.apache.commons:commons-text:1.12.0")
            dependency("org.apache.commons:commons-lang3:3.17.0")
            dependency("commons-codec:commons-codec:1.17.1")
            dependency("org.apache.poi:poi:5.3.0")
        }
    }

    repositories {
        mavenLocal()
        mavenCentral()
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

