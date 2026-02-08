package jay.chis.portfolio.cashflow.tracker.feature.account.application

import jay.chis.portfolio.cashflow.tracker.common.error.BusinessException
import jay.chis.portfolio.cashflow.tracker.common.error.ErrorCode
import jay.chis.portfolio.cashflow.tracker.feature.account.adapter.out.persistence.AccountRepositoryAdapter
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class DeleteAccountUseCase(
    private val accountRepository: AccountRepositoryAdapter
) {
    @Transactional
    fun delete(userId: UUID, accountId: UUID) {
        val exists = accountRepository.existsByIdAndUserId(accountId, userId)
        if (!exists) {
            throw BusinessException(
                code = ErrorCode.ACCOUNT_NOT_FOUND,
                message = "Account not found: $accountId"
            )
        }

        accountRepository.deleteById(accountId)
    }
}
