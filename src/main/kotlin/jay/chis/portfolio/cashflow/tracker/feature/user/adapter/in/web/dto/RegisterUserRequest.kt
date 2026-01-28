package jay.chis.portfolio.cashflow.tracker.feature.user.adapter.`in`.web.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class RegisterUserRequest(
    @field:Email
    @field:NotBlank
    val email: String,

    @field:NotBlank
    val nickname: String,

    @field:NotBlank
    val password: String
)