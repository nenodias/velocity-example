val velocityVersion = "2.3"
val velocityToolsVerion = "3.1"

plugins {
	java
	id("org.springframework.boot") version "2.7.10"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "io.github.nenodias"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
	maven(url = "https://mvnrepository.com")
}


dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.apache.velocity:velocity-engine-core:$velocityVersion")
	implementation("org.apache.velocity.tools:velocity-tools-generic:$velocityToolsVerion")
	implementation("org.apache.velocity.tools:velocity-tools-view:$velocityToolsVerion")


	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
