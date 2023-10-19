plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://repo.kotlin.link")
    mavenCentral()
}

dependencies {

    implementation("space.kscience:kmath-core:0.3.1-dev-RC")
    implementation("space.kscience:kmath-optimization:0.3.1-dev-RC")
}



tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}