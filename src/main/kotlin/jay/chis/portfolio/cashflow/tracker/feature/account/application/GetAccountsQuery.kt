package jay.chis.portfolio.cashflow.tracker.feature.account.application

import jay.chis.portfolio.cashflow.tracker.common.error.BusinessException
import jay.chis.portfolio.cashflow.tracker.common.error.ErrorCode
import jay.chis.portfolio.cashflow.tracker.feature.account.adapter.out.persistence.AccountJpaRepository
import jay.chis.portfolio.cashflow.tracker.feature.account.domain.Account
import jay.chis.portfolio.cashflow.tracker.feature.user.adapter.out.persistence.UserJpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class GetAccountsQuery(
    private val accountRepository: AccountJpaRepository,
    private val userRepository: UserJpaRepository
) {
    @Transactional(readOnly = true)
    fun getAll(userId: UUID): List<Account> {
        if (!userRepository.existsById(userId)) {
            throw BusinessException(ErrorCode.USER_NOT_FOUND, "User not found: $userId")
        }
        return accountRepository.findAllByUserId(userId)
    }
}