package jay.chis.portfolio.cashflow.tracker.feature.user.application

import jay.chis.portfolio.cashflow.tracker.feature.user.adapter.out.persistence.UserRepositoryAdapter
import jay.chis.portfolio.cashflow.tracker.feature.user.domain.User
import jay.chis.portfolio.cashflow.tracker.feature.user.domain.UserId
import org.springframework.stereotype.Service


@Service
class RegisterUserUseCase(
    private val userRepository: UserRepositoryAdapter
) {
    @Transactional
    fun register(email: String, password: String, nickname: String?): User {
        if (userRepository.existsByEmail(email)) {
            throw BusinessException(ErrorCode.DUPLICATE_EMAIL, "Email already exists: $email")
        }

        val user = User(
            id = UserId.new(),
            email = email.trim(),
            nickname = nickname?.trim()?.takeIf { it.isNotBlank() },
            password = password
        )
        return userRepository.save(user)
    }
}