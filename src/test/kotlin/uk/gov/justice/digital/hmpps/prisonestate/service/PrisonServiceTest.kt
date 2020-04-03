package uk.gov.justice.digital.hmpps.prisonestate.service

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import uk.gov.justice.digital.hmpps.prisonestate.jpa.Prison
import uk.gov.justice.digital.hmpps.prisonestate.jpa.PrisonGpPractice
import uk.gov.justice.digital.hmpps.prisonestate.jpa.PrisonRepository
import uk.gov.justice.digital.hmpps.prisonestate.resource.PrisonDto
import java.util.*

class PrisonServiceTest {
  private val prisonRepository: PrisonRepository = mock()
  private val prisonService = PrisonService(prisonRepository)

  @Suppress("ClassName")
  @Nested
  inner class findById {
    @Test
    fun `find prison no gp practice`() {
      whenever(prisonRepository.findById(anyString())).thenReturn(
          Optional.of(Prison("MDI", "Name", true)))
      val prisonDto = prisonService.findById("MDI")
      assertThat(prisonDto).isEqualTo(PrisonDto("MDI", "Name", true, null))
      verify(prisonRepository).findById("MDI")
    }

    @Test
    fun `find prison with gp practice`() {
      val prison = Prison("MDI", "Name", true)
      prison.gpPractice = PrisonGpPractice("MDI", "A12345")
      whenever(prisonRepository.findById(anyString())).thenReturn(
          Optional.of(prison))
      val prisonDto = prisonService.findById("MDI")
      assertThat(prisonDto).isEqualTo(PrisonDto("MDI", "Name", true, "A12345"))
    }

    @Test
    fun `find prison not found`() {
      whenever(prisonRepository.findById(anyString())).thenReturn(
          Optional.of(Prison("MDI", "Name", true)))
      val prisonDto = prisonService.findById("MDI")
      assertThat(prisonDto).isEqualTo(PrisonDto("MDI", "Name", true, null))
    }
  }

  @Suppress("ClassName")
  @Nested
  inner class findByGpPractice {
    @Test
    fun `find prison from gp practice`() {
      val prison = Prison("MDI", "Name", true)
      prison.gpPractice = PrisonGpPractice("MDI", "A12345")
      whenever(prisonRepository.findByGpPracticeGpPracticeCode(anyString())).thenReturn(
          Optional.of(prison))
      val prisonDto = prisonService.findByGpPractice("MDI")
      assertThat(prisonDto).isEqualTo(PrisonDto("MDI", "Name", true, "A12345"))
    }

    @Test
    fun `find prison not found`() {
      whenever(prisonRepository.findByGpPracticeGpPracticeCode(anyString())).thenReturn(
          Optional.of(Prison("MDI", "Name", true)))
      val prisonDto = prisonService.findByGpPractice("MDI")
      assertThat(prisonDto).isEqualTo(PrisonDto("MDI", "Name", true, null))
    }
  }
}
