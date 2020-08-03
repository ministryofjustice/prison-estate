plugins {
  id("uk.gov.justice.hmpps.gradle-spring-boot") version "0.4.7"
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

  implementation("org.springdoc:springdoc-openapi-ui:1.4.3")
  implementation("org.springdoc:springdoc-openapi-data-rest:1.4.3")
  implementation("org.springdoc:springdoc-openapi-kotlin:1.4.3")

  runtimeOnly("com.h2database:h2:1.4.200")
  runtimeOnly("org.flywaydb:flyway-core:6.5.3")
  runtimeOnly("org.postgresql:postgresql:42.2.14")

  testImplementation("org.springframework.boot:spring-boot-starter-webflux")
}
