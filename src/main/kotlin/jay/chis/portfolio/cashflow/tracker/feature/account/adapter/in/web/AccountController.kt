package jay.chis.portfolio.cashflow.tracker.feature.account.adapter.`in`.web

import jakarta.validation.Valid
import jay.chis.portfolio.cashflow.tracker.feature.account.adapter.`in`.web.dto.AccountResponse
import jay.chis.portfolio.cashflow.tracker.feature.account.adapter.`in`.web.dto.CreateAccountRequest
import jay.chis.portfolio.cashflow.tracker.feature.account.adapter.`in`.web.dto.UpdateAccountRequest
import jay.chis.portfolio.cashflow.tracker.feature.account.application.CreateAccountUseCase
import jay.chis.portfolio.cashflow.tracker.feature.account.application.DeleteAccountUseCase
import jay.chis.portfolio.cashflow.tracker.feature.account.application.GetAccountsQuery
import jay.chis.portfolio.cashflow.tracker.feature.account.application.UpdateAccountUseCase
import jay.chis.portfolio.cashflow.tracker.feature.account.domain.Account
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@Validated
@RequestMapping("/api/users/{userId}/accounts")
class AccountController(
    private val createAccountUseCase: CreateAccountUseCase,
    private val updateAccountUseCase: UpdateAccountUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase,
    private val getAccountsQuery: GetAccountsQuery
) {

    @PostMapping
    fun create(
        @PathVariable userId: UUID,
        @RequestBody @Valid request: CreateAccountRequest
    ): ResponseEntity<AccountResponse> {
        val created = createAccountUseCase.create(
            userId = userId,
            name = request.name,
            type = request.type
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(created.toResponse())
    }

    @GetMapping
    fun getAll(
        @PathVariable userId: UUID
    ): ResponseEntity<List<AccountResponse>> {
        val accounts = getAccountsQuery.getAll(userId)
        return ResponseEntity.ok(accounts.map { it.toResponse() })
    }

    @PutMapping("/{accountId}")
    fun update(
        @PathVariable userId: UUID,
        @PathVariable accountId: UUID,
        @RequestBody @Valid request: UpdateAccountRequest
    ): ResponseEntity<AccountResponse> {
        val updated = updateAccountUseCase.update(
            userId = userId,
            accountId = accountId,
            name = request.name,
            type = request.type
        )
        return ResponseEntity.ok(updated.toResponse())
    }

    @DeleteMapping("/{accountId}")
    fun delete(
        @PathVariable userId: UUID,
        @PathVariable accountId: UUID
    ): ResponseEntity<Void> {
        deleteAccountUseCase.delete(userId, accountId)
        return ResponseEntity.noContent().build()
    }

    private fun Account.toResponse(): AccountResponse =
        AccountResponse(
            id = this.id,
            userId = this.userId,
            name = this.name,
            type = this.type
        )
}