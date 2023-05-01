plugins {
	java
	id("org.liquibase.gradle") version "2.0.2"
	id("org.springframework.boot") version "3.0.6"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.liquibase:liquibase-core:4.20.0")
	implementation("org.liquibase:liquibase-groovy-dsl:3.0.3")
	implementation("org.postgresql:postgresql:42.5.4")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.0")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
}
tasks.withType<Test> {
	useJUnitPlatform()
}
repositories {
	mavenCentral()
}


