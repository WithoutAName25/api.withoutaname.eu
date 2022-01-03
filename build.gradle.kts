val versionString: String by project

plugins {
    application
    kotlin("jvm") version "1.6.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"
}

group = "eu.withoutaname.api"
version = versionString
application {
    mainClass.set("eu.withoutaname.api.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test"))

    implementation("ch.qos.logback:logback-classic:1.2.10")

    val ktor="2.0.0-beta-1"
    implementation("io.ktor:ktor-server-core:$ktor")
    implementation("io.ktor:ktor-server-auth:$ktor")
    implementation("io.ktor:ktor-server-sessions:$ktor")
    implementation("io.ktor:ktor-server-locations:$ktor")
    implementation("io.ktor:ktor-server-host-common:$ktor")
    implementation("io.ktor:ktor-server-status-pages:$ktor")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor")
    implementation("io.ktor:ktor-server-call-logging:$ktor")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor")
    implementation("io.ktor:ktor-server-netty:$ktor")
    testImplementation("io.ktor:ktor-server-test-host:$ktor")
}

tasks.test {
    useJUnitPlatform()
}