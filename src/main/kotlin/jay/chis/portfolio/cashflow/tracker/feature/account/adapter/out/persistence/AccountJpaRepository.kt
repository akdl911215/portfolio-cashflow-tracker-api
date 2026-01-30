package jay.chis.portfolio.cashflow.tracker.feature.account.adapter.out.persistence

import jay.chis.portfolio.cashflow.tracker.feature.account.domain.Account
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface AccountJpaRepository: JpaRepository<AccountJpaEntity, UUID> {
    fun save(account: Account): Account
    fun findAllByUserId(userId: UUID): List<Account>
    fun findById(accountId: UUID): Account?
    fun existsByIdAndUserId(accountId: UUID, userId: UUID): Boolean
    fun deleteById(accountId: UUID)
}