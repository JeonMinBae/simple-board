import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
    id ("org.asciidoctor.jvm.convert") version "3.3.2"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

//ext {
//    set("snippetsDir", file("build/generated-snippets"))
//}
//
//asciidoctor {
//    inputs.dir snippetsDir
//            configurations "asciidoctorExtensions"
//    dependsOn test
//            baseDirFollowsSourceFile()
//}
//
//asciidoctor.doFirst {
//    delete file("src/main/resources/static/docs")
//}
//
//// asciidoctor 작업 이후 생성된 HTML 파일을 static/docs 로 copy
//task copyDocument(type: Copy) {
//    dependsOn asciidoctor
//            from file("build/docs/asciidoc")
//    into file("src/main/resources/static/docs")
//}
//
//build {
//    dependsOn copyDocument
//}
//
//test {
//    outputs.dir snippetsDir
//            useJUnitPlatform()
//}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    runtimeOnly("com.h2database:h2")

    // jwt
    implementation("io.jsonwebtoken:jjwt:0.12.5")

    // rest docs
//    asciidoctorExtensions ("org.springframework.restdocs:spring-restdocs-asciidoctor")
//    testImplementation ("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
