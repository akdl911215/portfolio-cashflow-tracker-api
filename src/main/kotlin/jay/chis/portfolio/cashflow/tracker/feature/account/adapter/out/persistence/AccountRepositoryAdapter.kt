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
        return toDomain(saved)
    }

    fun findById(accountId: UUID): Account? =
        accountJpaRepository.findById(accountId).orElse(null)?.let { entity ->
            toDomain(entity)
        }

    fun findAllByUserId(userId: UUID): List<Account> =
        accountJpaRepository.findAllByUserId(userId)
            .map { entity -> toDomain(entity) }

    fun existsByIdAndUserId(accountId: UUID, userId: UUID): Boolean =
        accountJpaRepository.existsByIdAndUserId(accountId, userId)

    fun deleteById(accountId: UUID) {
        accountJpaRepository.deleteById(accountId)
    }

    private fun Account.toEntity(): AccountJpaEntity =
        AccountJpaEntity(
            id = this.id,
            userId = this.userId,
            name = this.name,
            type = this.type
        )

    private fun toDomain(entity: AccountJpaEntity): Account =
        Account(
            id = entity.id,
            userId = entity.userId,
            name = entity.name,
            type = entity.type
        )
}
