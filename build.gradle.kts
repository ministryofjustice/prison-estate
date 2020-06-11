plugins {
  id("uk.gov.justice.hmpps.gradle-spring-boot") version "0.4.1"
  kotlin("plugin.spring") version "1.3.72"
  kotlin("plugin.jpa") version "1.3.72"
}

dependencyCheck {
  suppressionFiles = listOf() // Turn off dependency suppression files - none are currently relevant
}

configurations {
  implementation { exclude(mapOf("module" to "tomcat-jdbc")) }
  testImplementation { exclude(group = "org.junit.vintage") }
}

dependencies {
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

  implementation("org.springframework.boot:spring-boot-starter-data-jpa")

  implementation("io.springfox:springfox-swagger2:2.9.2")
  implementation("io.springfox:springfox-swagger-ui:2.9.2")
  implementation("io.springfox:springfox-bean-validators:2.9.2")

  runtimeOnly("com.h2database:h2:1.4.200")
  runtimeOnly("org.flywaydb:flyway-core:6.4.4")
  runtimeOnly("org.postgresql:postgresql:42.2.14")

  testImplementation("org.springframework.boot:spring-boot-starter-webflux")
}
