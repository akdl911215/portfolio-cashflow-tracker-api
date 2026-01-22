package jay.chis.portfolio.cashflow.tracker.feature.user.application

import jay.chis.portfolio.cashflow.tracker.feature.user.adapter.out.persistence.UserRepositoryAdapter
import jay.chis.portfolio.cashflow.tracker.feature.user.domain.User
import jay.chis.portfolio.cashflow.tracker.feature.user.domain.UserId
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetUserQuery(
    private val userRepository: UserRepositoryAdapter
) {
    fun getById(id: UUID): User {
        return userRepository.findById(UserId(id))
            ?: throw BusinessException(ErrorCode.USER_NOT_FOUND, "User not found: $id")
    }
}