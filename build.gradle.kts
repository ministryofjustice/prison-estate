plugins {
  id("uk.gov.justice.hmpps.gradle-spring-boot") version "2.1.0"
  kotlin("plugin.spring") version "1.4.21"
  kotlin("plugin.jpa") version "1.4.21"
}

configurations {
  implementation { exclude(mapOf("module" to "tomcat-jdbc")) }
  testImplementation { exclude(group = "org.junit.vintage") }
}

dependencies {
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

  implementation("org.springframework.boot:spring-boot-starter-data-jpa")

  implementation("org.springdoc:springdoc-openapi-ui:1.5.1")
  implementation("org.springdoc:springdoc-openapi-data-rest:1.5.1")
  implementation("org.springdoc:springdoc-openapi-kotlin:1.5.1")

  runtimeOnly("com.h2database:h2:1.4.200")
  runtimeOnly("org.flywaydb:flyway-core:7.3.1")
  runtimeOnly("org.postgresql:postgresql:42.2.18")

  testImplementation("org.springframework.boot:spring-boot-starter-webflux")
}
