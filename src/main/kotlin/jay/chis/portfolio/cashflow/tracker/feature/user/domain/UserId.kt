package jay.chis.portfolio.cashflow.tracker.feature.user.domain

import java.util.UUID

@JvmInline
value class UserId(val value: UUID) {
    override fun toString(): String = value.toString()

    companion object {
        fun new(): UserId = UserId(UUID.randomUUID())
    }
}