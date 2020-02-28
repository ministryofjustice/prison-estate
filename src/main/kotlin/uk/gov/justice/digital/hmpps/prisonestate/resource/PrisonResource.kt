package uk.gov.justice.digital.hmpps.prisonestate.resource

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import io.swagger.annotations.*
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import uk.gov.justice.digital.hmpps.prisonestate.ErrorResponse
import uk.gov.justice.digital.hmpps.prisonestate.jpa.Prison
import uk.gov.justice.digital.hmpps.prisonestate.service.PrisonService
import javax.validation.constraints.Size

@RestController
@Validated
@RequestMapping("/prisons", produces = [MediaType.APPLICATION_JSON_VALUE])
class PrisonResource(private val prisonService: PrisonService) {
  @GetMapping("/id/{prisonId}")
  @ApiOperation("Get specified prison")
  @ApiResponses(value = [
    ApiResponse(code = 400, message = "Bad request.  Wrong format for prison_id.", response = ErrorResponse::class),
    ApiResponse(code = 404, message = "Prison not found.", response = ErrorResponse::class)
  ])
  fun getPrisonFromId(@ApiParam("Prison ID", example = "MDI") @PathVariable @Size(max = 3) prisonId: String): PrisonDto =
      prisonService.findById(prisonId)

  @GetMapping("/gp-practice/{gpPracticeCode}")
  @ApiOperation("Get specified prison from GP practice code")
  @ApiResponses(value = [
    ApiResponse(code = 400, message = "Bad request.  Wrong format for GP practice code.", response = ErrorResponse::class),
    ApiResponse(code = 404, message = "No prison linked to the GP practice code.", response = ErrorResponse::class)
  ])
  fun getPrisonFromGpPrescriber(@ApiParam("GP Practice Code", example = "Y05537") @PathVariable @Size(max = 6) gpPracticeCode: String): PrisonDto =
      prisonService.findByGpPractice(gpPracticeCode)
}

@ApiModel("Prison and GP Practice Information")
@JsonInclude(NON_NULL)
data class PrisonDto(
    @ApiModelProperty("Prison ID", example = "MDI", position = 1, required = true) val prisonId: String,
    @ApiModelProperty("Name of the prison", example = "Moorland (HMP & YOI)", position = 2, required = true) val name: String,
    @ApiModelProperty("Whether the prison is still active", position = 3, required = true) val active: Boolean,
    @ApiModelProperty("GP Practice Code", example = "Y05537", position = 4) val gpPracticeCode: String?
) {
  constructor(prison: Prison) : this(prison.prisonId, prison.name, prison.active, prison.gpPractice?.gpPracticeCode)
}