plugins {
  id("uk.gov.justice.digital.hmpps.gradle.DpsSpringBoot") version "0.0.1-SNAPSHOT"
  kotlin("plugin.spring") version "1.3.71"
  kotlin("plugin.jpa") version "1.3.71"
}

configurations {
  implementation { exclude(mapOf("module" to "tomcat-jdbc")) }
}

dependencies {
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

  implementation("org.springframework.boot:spring-boot-starter-data-jpa")

  implementation("io.springfox:springfox-swagger2:2.9.2")
  implementation("io.springfox:springfox-swagger-ui:2.9.2")
  implementation("io.springfox:springfox-bean-validators:2.9.2")

  runtimeOnly("com.h2database:h2:1.4.200")
  runtimeOnly("org.flywaydb:flyway-core:6.3.2")
  runtimeOnly("org.postgresql:postgresql:42.2.12")

  testImplementation("org.springframework.boot:spring-boot-starter-webflux")
}
