package jay.chis.portfolio.cashflow.tracker.feature.account.adapter.`in`.web.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import jay.chis.portfolio.cashflow.tracker.feature.account.domain.AccountType

data class UpdateAccountRequest(
    @field:NotBlank
    @field:Size(max = 100)
    val name: String,

    val type: AccountType
)