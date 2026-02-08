package jay.chis.portfolio.cashflow.tracker.feature.account.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface AccountJpaRepository : JpaRepository<AccountJpaEntity, UUID> {
    fun findAllByUserId(userId: UUID): List<AccountJpaEntity>
    fun existsByIdAndUserId(id: UUID, userId: UUID): Boolean
}
