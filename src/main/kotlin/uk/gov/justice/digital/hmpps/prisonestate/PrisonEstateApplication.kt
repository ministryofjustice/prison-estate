package uk.gov.justice.digital.hmpps.prisonestate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PrisonEstateApplication

fun main(args: Array<String>) {
  runApplication<PrisonEstateApplication>(*args)
}
