package jay.chis.portfolio.cashflow.tracker.feature.user.domain

data class User(
    val id: UserId,
    val email: String,
    val nickname: String,
    val password: String
)