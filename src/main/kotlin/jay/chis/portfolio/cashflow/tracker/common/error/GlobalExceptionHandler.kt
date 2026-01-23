package jay.chis.portfolio.cashflow.tracker.common.error

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException::class)
    fun handle(e: BusinessException): Map<String, String> {
        return mapOf(
            "code" to e.code.name,
            "message" to e.message.orEmpty()
        )
    }
}
