package jay.chis.portfolio.cashflow.tracker.feature.account.domain

import java.util.UUID

data class Account(
    val id: UUID,
    val userId: UUID,
    val name: String,
    val type: AccountType
)