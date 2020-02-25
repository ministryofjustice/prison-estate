package uk.gov.justice.digital.hmpps.prisonestate.services.health

import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.ISO_DATE

class HealthIntTest : IntegrationTest() {
  @Test
  fun `Health page reports ok`() {
    webTestClient.get().uri("/health")
        .exchange()
        .expectStatus().isOk
        .expectBody().jsonPath("status").isEqualTo("UP")
  }

  @Test
  fun `Health ping reports ok`() {
    webTestClient.get().uri("/health/ping")
        .exchange()
        .expectStatus().isOk
        .expectBody().jsonPath("status").isEqualTo("UP")
  }

  @Test
  fun `Health info reports version`() {
    webTestClient.get().uri("/health")
        .exchange()
        .expectStatus().isOk
        .expectBody().jsonPath("components.healthInfo.details.version").isEqualTo(LocalDateTime.now().format(ISO_DATE))
  }
}
