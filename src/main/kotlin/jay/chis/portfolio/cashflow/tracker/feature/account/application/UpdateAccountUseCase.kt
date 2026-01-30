package jay.chis.portfolio.cashflow.tracker.feature.account.application

import jay.chis.portfolio.cashflow.tracker.common.error.BusinessException
import jay.chis.portfolio.cashflow.tracker.common.error.ErrorCode
import jay.chis.portfolio.cashflow.tracker.feature.account.adapter.out.persistence.AccountJpaRepository
import jay.chis.portfolio.cashflow.tracker.feature.account.domain.Account
import jay.chis.portfolio.cashflow.tracker.feature.account.domain.AccountType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class UpdateAccountUseCase(
    private val accountRepository: AccountJpaRepository
) {
    @Transactional
    fun update(userId: UUID, accountId: UUID, name: String, type: AccountType): Account {
        if (!accountRepository.existsByIdAndUserId(accountId, userId)) {
            throw BusinessException(ErrorCode.ACCOUNT_NOT_FOUND, "Account not found: $accountId")
        }

        val existing = accountRepository.findById(accountId)
            ?: throw BusinessException(ErrorCode.ACCOUNT_NOT_FOUND, "Account not found: $accountId")

        val updated = existing.copy(
            name = name.trim(),
            type = type
        )

        return accountRepository.save(updated)
    }
}