plugins {
    java
    id("org.springframework.boot") version "2.7.8"
    id("io.spring.dependency-management") version "1.1.0"
    id("com.google.cloud.tools.appengine-appenginewebxml") version "2.4.5"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

appengine {
    stage {
//        setArtifact(tasks.bootJar.map { j -> j.archiveFile.get().asFile })
    }
    deploy {
        projectId = "runeforgeapp"
        version = "test"
        promote = false
    }
}


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.google.appengine:appengine-api-1.0-sdk:2.0.10")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.appengineStage {
    dependsOn(tasks.bootJar)
}