package uk.gov.justice.digital.hmpps.prisonestate.resource

import com.nhaarman.mockito_kotlin.whenever
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.springframework.boot.test.mock.mockito.MockBean
import uk.gov.justice.digital.hmpps.prisonestate.jpa.Prison
import uk.gov.justice.digital.hmpps.prisonestate.jpa.PrisonGpPractice
import uk.gov.justice.digital.hmpps.prisonestate.jpa.PrisonRepository
import java.util.*

class PrisonResourceTest : IntegrationTest() {
  @MockBean
  private lateinit var prisonRepository: PrisonRepository

  @Suppress("ClassName")
  @Nested
  inner class findById {

    @Test
    fun `find prison`() {
      val prison = Prison("MDI", "Moorland (HMP & YOI)", true)
      prison.gpPractice = PrisonGpPractice("MDI", "Y05537")
      whenever(prisonRepository.findById(anyString())).thenReturn(
          Optional.of(prison)
      )
      webTestClient.get().uri("/prisons/id/MDI")
          .exchange()
          .expectStatus().isOk
          .expectBody().json("prison_id_mdi".loadJson())
    }

    @Test
    fun `find prison no gp practice mapped`() {
      val prison = Prison("MDI", "Moorland (HMP & YOI)", true)
      whenever(prisonRepository.findById(anyString())).thenReturn(
          Optional.of(prison)
      )
      webTestClient.get().uri("/prisons/id/MDI")
          .exchange()
          .expectStatus().isOk
          .expectBody().json("prison_id_mdi_no_gp".loadJson())
    }

    @Test
    fun `find prison validation failure`() {
      webTestClient.get().uri("/prisons/id/1234")
          .exchange()
          .expectStatus().isBadRequest
          .expectBody().json("prison_id_badrequest".loadJson())
    }
  }

  private fun String.loadJson(): String =
      PrisonResourceTest::class.java.getResource("$this.json").readText()
}
