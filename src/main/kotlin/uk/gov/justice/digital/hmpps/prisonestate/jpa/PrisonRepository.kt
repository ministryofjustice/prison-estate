package uk.gov.justice.digital.hmpps.prisonestate.jpa

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

@Repository
interface PrisonRepository : CrudRepository<Prison, String> {
  fun findByActiveOrderByPrisonId(active: Boolean): List<Prison>

  fun findByGpPracticeGpPracticeCode(gpPracticeCode: String): Optional<Prison>
}

@Entity
data class Prison(
  @Id
  val prisonId: String,
  val name: String,
  val active: Boolean
) {

  @OneToOne
  @JoinColumn("prison_id")
  var gpPractice: PrisonGpPractice? = null
}

@Entity
data class PrisonGpPractice(
  @Id
  val prisonId: String,
  val gpPracticeCode: String
)
