plugins {
    application
}

group = "ru.white-logic"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

application {
    mainClass = "ru.white_logic.Main"
}

tasks.withType<Jar> {
    manifest {
        attributes["Implementation-Version"] = archiveVersion
    }
}
