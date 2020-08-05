package uk.gov.justice.digital.hmpps.prisonestate

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class OpenApiConfiguration(buildProperties: BuildProperties) {
  private val version: String = buildProperties.version

  @Bean
  fun customOpenAPI(buildProperties: BuildProperties): OpenAPI =
      OpenAPI().info(
          Info().title("Prison Estate API Documentation").version(version).description(
              "API for providing Prison Estate information."
          )
              .contact(Contact().name("HMPPS Digital Studio").email("feedback@digital.justice.gov.uk"))
      )
}

