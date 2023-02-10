plugins {
    java
    id("org.springframework.boot") version "3.0.2"
    id("io.spring.dependency-management") version "1.1.0"
    id("com.google.cloud.tools.appengine") version "2.4.5"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

appengine {
    stage {
        setArtifact(tasks.bootJar.map { j -> j.archiveFile.get().asFile })
    }
    deploy {
        projectId = "runeforgeapp"
        version = "test17"
        promote = false
    }
}


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.appengineStage {
    dependsOn(tasks.bootJar)
}