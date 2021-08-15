import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import java.net.URI

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposedVersion: String by project
val koin_version: String by project

plugins {
    base
    idea
    application
    kotlin("jvm") version "1.5.21"
    kotlin("plugin.serialization") version "1.5.21"
    id("com.palantir.docker") version "0.25.0"
    id("com.palantir.docker-compose") version "0.25.0"
    id("com.github.johnrengelman.shadow") version "6.0.0"
}

buildscript {
    repositories {
        jcenter()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath("org.jetbrains.kotlin", "kotlin-gradle-plugin", "1.5.21")
    }
}

application {
    group = "com.thelonedev"
    version = "1.0.0"
    mainClassName = "com.thelonedev.ApplicationKt"
}

repositories {
    jcenter()
    mavenCentral()
    maven { url = URI("https://kotlin.bintray.com/ktor") }
}

dependencies {
    implementation("io.ktor:ktor-locations:$ktor_version")
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-host-common:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.2.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.0")
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
    implementation("mysql:mysql-connector-java:8.0.19")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
}

tasks {
    withType<ShadowJar> {
        archiveFileName.set("api-salary.jar")

        manifest {
            attributes(
                mapOf("Main-Class" to application.mainClassName)
            )
        }
    }

    docker {
        name = "api-salary"
        setDockerfile(File("./Dockerfile"))
        copySpec.from(shadowJar.get().archiveFile).into("./")
    }

    dockerComposeUp {
        dependsOn("docker")
    }
}
