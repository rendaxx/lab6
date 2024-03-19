plugins {
    id("java")
    id("io.freefair.lombok") version "8.6"
}

group = "ru.rendaxx"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.opencsv:opencsv:5.9")
    implementation("org.apache.commons:commons-lang3:3.14.0")
    implementation(project(":common"))
}

tasks.test {
    useJUnitPlatform()
}