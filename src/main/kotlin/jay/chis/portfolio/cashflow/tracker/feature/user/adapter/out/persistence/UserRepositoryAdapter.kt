package jay.chis.portfolio.cashflow.tracker.feature.user.adapter.out.persistence

import jay.chis.portfolio.cashflow.tracker.feature.user.domain.User
import jay.chis.portfolio.cashflow.tracker.feature.user.domain.UserId
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class UserRepositoryAdapter(
    private val userJpaRepository: UserJpaRepository
) {
    fun save(user: User): User {
        val saved = userJpaRepository.save(user.toEntity())
        return saved.toDomain()
    }

    fun findById(id: UserId): User? =
        userJpaRepository.findById(id.value).orElse(null)?.toDomain()

    fun existsByEmail(email: String): Boolean =
        userJpaRepository.existsByEmail(email)

    private fun User.toEntity(): UserJpaEntity =
        UserJpaEntity(
            id = this.id.value,
            email = this.email,
            nickname = this.nickname,
            password = this.password,
        )

    private fun UserJpaEntity.toDomain(): User =
        User(
            id = UserId(this.id),
            email = this.email,
            nickname = this.nickname,
            password = this.password
        )
}