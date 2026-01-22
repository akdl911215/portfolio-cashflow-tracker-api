package jay.chis.portfolio.cashflow.tracker.feature.user.adapter.out.persistence

import java.util.UUID

interface UserJpaRepository : JpaRepository<UserJpaEntity, UUID> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): UserJpaEntity?
}