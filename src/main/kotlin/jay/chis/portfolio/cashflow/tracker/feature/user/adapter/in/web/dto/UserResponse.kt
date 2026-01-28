package jay.chis.portfolio.cashflow.tracker.feature.user.adapter.`in`.web.dto

import jay.chis.portfolio.cashflow.tracker.feature.user.domain.User

data class UserResponse(
    val id: String,
    val email: String,
    val nickname: String
) {
    companion object {
        fun from(user: User) = UserResponse(
            id = user.id.toString(),
            email = user.email,
            nickname = user.nickname
        )
    }
}