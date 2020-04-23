package uk.gov.justice.digital.hmpps.prisonestate.service

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.junit.MockitoJUnitRunner
import uk.gov.justice.digital.hmpps.prisonestate.jpa.Prison
import uk.gov.justice.digital.hmpps.prisonestate.jpa.PrisonRepository
import uk.gov.justice.digital.hmpps.prisonestate.resource.PrisonDto
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class PrisonServiceJunit4Test {
  private val prisonRepository: PrisonRepository = mock()
  private val prisonService = PrisonService(prisonRepository)

    @Test
    fun `find prison no gp practice`() {
      whenever(prisonRepository.findById(anyString())).thenReturn(
          Optional.of(Prison("MDI", "Name", true)))
      val prisonDto = prisonService.findById("MDI")
      assertThat(prisonDto).isEqualTo(PrisonDto("MDI", "Name", true, null))
      verify(prisonRepository).findById("MDI")
    }
}
