package jay.chis.portfolio.cashflow.tracker.feature.user.adapter.`in`.web.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class RegisterUserRequest(
    @field:Email
    @field:NotBlank
    val email: String,

    val nickname: String? = null,

    val password: String
)