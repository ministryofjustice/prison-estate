package uk.gov.justice.digital.hmpps.prisonestate

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.persistence.EntityNotFoundException
import javax.validation.ValidationException

@RestControllerAdvice
class PrisonEstateExceptionHandler {
  @ExceptionHandler(EntityNotFoundException::class)
  fun handleNotFoundException(e: Exception): ResponseEntity<ErrorResponse> {
    log.debug("Prison not found exception: {}", e.message)
    return ResponseEntity
      .status(HttpStatus.NOT_FOUND)
      .body(
        ErrorResponse(
          status = HttpStatus.NOT_FOUND,
          userMessage = "Prison not found: ${e.message}",
          developerMessage = e.message
        )
      )
  }

  @ExceptionHandler(ValidationException::class)
  fun handleValidationException(e: java.lang.Exception): ResponseEntity<ErrorResponse?>? {
    log.info("Validation exception: {}", e.message)
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(
        ErrorResponse(
          status = HttpStatus.BAD_REQUEST,
          userMessage = "Validation failure: ${e.message}",
          developerMessage = e.message
        )
      )
  }

  companion object {
    private val log = LoggerFactory.getLogger(PrisonEstateExceptionHandler::class.java)
  }
}

data class ErrorResponse(
  val status: Int,
  val errorCode: Int? = null,
  val userMessage: String? = null,
  val developerMessage: String? = null,
  val moreInfo: String? = null
) {
  constructor(
    status: HttpStatus,
    errorCode: Int? = null,
    userMessage: String? = null,
    developerMessage: String? = null,
    moreInfo: String? = null
  ) :
    this(status.value(), errorCode, userMessage, developerMessage, moreInfo)
}
