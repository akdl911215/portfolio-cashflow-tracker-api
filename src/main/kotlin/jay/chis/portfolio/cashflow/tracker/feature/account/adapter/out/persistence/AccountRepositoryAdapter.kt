package jay.chis.portfolio.cashflow.tracker.feature.account.adapter.out.persistence

import jay.chis.portfolio.cashflow.tracker.feature.account.domain.Account
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class AccountRepositoryAdapter(
    private val accountJpaRepository: AccountJpaRepository
) {
    fun save(account: Account): Account {
        val saved = accountJpaRepository.save(account.toEntity())
        return saved.toDomain()
    }

    fun findAllByUserId(userId: UUID): List<Account> =
        accountJpaRepository.findAllByUserId(userId)
            .map { entity -> entity.toDomain() }

    fun existsByIdAndUserId(accountId: UUID, userId: UUID): Boolean =
        accountJpaRepository.existsByIdAndUserId(accountId, userId)

    private fun Account.toEntity(): AccountJpaEntity =
        AccountJpaEntity(
            id = this.id,
            userId = this.userId,
            name = this.name,
            type = this.type
        )

    private fun AccountJpaEntity.toDomain(): Account =
        Account(
            id = this.id,
            userId = this.userId,
            name = this.name,
            type = this.type
        )
}
