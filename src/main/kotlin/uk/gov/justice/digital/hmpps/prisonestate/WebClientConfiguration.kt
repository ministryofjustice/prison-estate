package uk.gov.justice.digital.hmpps.prisonestate

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.hibernate.validator.constraints.URL
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClient.Builder
import reactor.netty.http.client.HttpClient
import java.time.Duration

@Configuration
class WebClientConfiguration(
  @Value("\${api.base.url}") private val baseUrl: @URL String,
  @Value("\${api.health-timeout:1s}") private val healthTimeout: Duration
) {

  @Bean
  fun pingWebClient(builder: Builder): WebClient = createHealthClient(builder, baseUrl)

  private fun createHealthClient(builder: Builder, url: @URL String): WebClient {
    val httpClient = HttpClient.create()
      .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, healthTimeout.toMillis().toInt())
      .doOnConnected { connection ->
        connection.addHandlerLast(ReadTimeoutHandler(healthTimeout.toSeconds().toInt()))
          .addHandlerLast(WriteTimeoutHandler(healthTimeout.toSeconds().toInt()))
      }
    return builder.clientConnector(ReactorClientHttpConnector(httpClient))
      .baseUrl(url).build()
  }
}
