package jay.chis.portfolio.cashflow.tracker.feature.user.adapter.`in`.web

@RestController
@RequestMapping("/api/users")
class UserController(
    private val registerUserUseCase: RegisterUserUseCase,
    private val getUserQuery: GetUserQuery
) {
    @PostMapping
    fun register(@RequestBody @Valid req: RegisterUserRequest): UserResponse {
        val user = registerUserUseCase.register(req.email, req.nickname)
        return UserResponse.from(user)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): UserResponse {
        val user = getUserQuery.getById(id)
        return UserResponse.from(user)
    }
}