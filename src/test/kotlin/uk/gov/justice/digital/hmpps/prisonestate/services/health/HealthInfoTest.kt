package uk.gov.justice.digital.hmpps.prisonestate.services.health

import org.assertj.core.api.Assertions.assertThat
import org.springframework.boot.info.BuildProperties
import java.util.*

class HealthInfoTest {
  @org.junit.Test
  fun `should include version info`() {
    val properties = Properties()
    properties.setProperty("version", "somever")
    assertThat(HealthInfo(BuildProperties(properties)).health().details).isEqualTo(mapOf("version" to "somever"))
  }
}
