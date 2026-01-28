package jay.chis.portfolio.cashflow.tracker.common.error

import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBusiness(e: BusinessException): Map<String, String> =
        mapOf("code" to e.code.name, "message" to (e.message ?: e.code.message))

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidation(e: MethodArgumentNotValidException): Map<String, String> {
        val first = e.bindingResult.fieldErrors.firstOrNull()
        val message = first?.defaultMessage ?: "Validation failed"
        return mapOf(
            "code" to "VALIDATION_FAILED",
            "message" to message
        )
    }
}