package jay.chis.portfolio.cashflow.tracker.feature.account.application

import jakarta.transaction.Transactional
import jay.chis.portfolio.cashflow.tracker.common.error.BusinessException
import jay.chis.portfolio.cashflow.tracker.common.error.ErrorCode
import jay.chis.portfolio.cashflow.tracker.feature.account.adapter.out.persistence.AccountJpaRepository
import jay.chis.portfolio.cashflow.tracker.feature.account.domain.Account
import jay.chis.portfolio.cashflow.tracker.feature.account.domain.AccountType
import jay.chis.portfolio.cashflow.tracker.feature.user.adapter.out.persistence.UserJpaRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CreateAccountUseCase(
    private val accountRepository: AccountJpaRepository,
    private val userRepository: UserJpaRepository
) {
    @Transactional
    fun create(userId: UUID, name: String, type: AccountType): Account {
        if (!userRepository.existsById(userId)) {
            throw BusinessException(ErrorCode.USER_NOT_FOUND, "User not found: $userId")
        }

        val account = Account(
            id = UUID.randomUUID(),
            userId = userId,
            name = name.trim(),
            type = type
        )

        return accountRepository.save(account)
    }
}