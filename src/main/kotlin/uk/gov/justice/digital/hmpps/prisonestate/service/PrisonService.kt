package uk.gov.justice.digital.hmpps.prisonestate.service

import org.springframework.stereotype.Service
import uk.gov.justice.digital.hmpps.prisonestate.jpa.PrisonRepository
import uk.gov.justice.digital.hmpps.prisonestate.resource.PrisonDto
import javax.persistence.EntityNotFoundException

@Service
class PrisonService(private val prisonRepository: PrisonRepository) {
  fun findById(prisonId: String): PrisonDto {
    val prison = prisonRepository.findById(prisonId).orElseThrow { EntityNotFoundException("Prison $prisonId not found") }
    return PrisonDto(prison)
  }
}
