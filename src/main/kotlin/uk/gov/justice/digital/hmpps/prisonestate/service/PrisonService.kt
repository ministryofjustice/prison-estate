package uk.gov.justice.digital.hmpps.prisonestate.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import uk.gov.justice.digital.hmpps.prisonestate.jpa.PrisonRepository
import uk.gov.justice.digital.hmpps.prisonestate.resource.PrisonDto
import javax.persistence.EntityNotFoundException

@Service
class PrisonService(
  private val prisonRepository: PrisonRepository,
  private val pingWebClient: WebClient,
) {
  fun findById(prisonId: String): PrisonDto {
    val prison = prisonRepository.findById(prisonId)
      .orElseThrow { EntityNotFoundException("Prison $prisonId not found") }
    val response = pingWebClient.get().uri("/health/diskSpace").retrieve().bodyToMono(String::class.java).block()
    log.info("Received response {} from diskSpace", response)
    return PrisonDto(prison)
  }

  fun findByGpPractice(gpPracticeCode: String): PrisonDto {
    val prison = prisonRepository.findByGpPracticeGpPracticeCode(gpPracticeCode)
      .orElseThrow { EntityNotFoundException("Prison with gp practice $gpPracticeCode not found") }
    return PrisonDto(prison)
  }

  companion object {
    private val log = LoggerFactory.getLogger(this::class.java)
  }
}
