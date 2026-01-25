package jay.chis.portfolio.cashflow.tracker.feature.user.adapter.`in`.web

import jakarta.validation.Valid
import jay.chis.portfolio.cashflow.tracker.feature.user.adapter.`in`.web.dto.RegisterUserRequest
import jay.chis.portfolio.cashflow.tracker.feature.user.adapter.`in`.web.dto.UserResponse
import jay.chis.portfolio.cashflow.tracker.feature.user.application.GetUserQuery
import jay.chis.portfolio.cashflow.tracker.feature.user.application.RegisterUserUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/users")
class UserController(
    private val registerUserUseCase: RegisterUserUseCase,
    private val getUserQuery: GetUserQuery
) {
    @PostMapping
    fun register(@RequestBody @Valid req: RegisterUserRequest): UserResponse {
        val user = registerUserUseCase.register(req.email, req.password, req.nickname)
        return UserResponse.from(user)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): UserResponse {
        val user = getUserQuery.getById(id)
        return UserResponse.from(user)
    }
}