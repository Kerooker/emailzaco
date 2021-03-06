import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.2.0.RC1"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	kotlin("jvm") version "1.3.50"
	kotlin("plugin.spring") version "1.3.50"
}

group = "br.org.institutoops"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
	// Kotlin
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	
	// Spring
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.postgresql:postgresql:42.2.5")
	
	runtimeOnly("com.h2database:h2")
	
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	
	// Jackson
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	// Front
	implementation("org.webjars:jquery:3.3.1")
	implementation("org.webjars:bootstrap:3.3.7")
	implementation("org.webjars:font-awesome:4.7.0")
	implementation("org.webjars.bower:jquery-mask-plugin:1.13.4")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	
	// Testing
	testImplementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")
	testImplementation("io.kotlintest:kotlintest-extensions-spring:3.4.2")
	testImplementation("io.kotlintest:kotlintest-assertions-json:3.4.2")
	testImplementation("com.ninja-squad:springmockk:1.1.3")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
