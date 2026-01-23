package jay.chis.portfolio.cashflow.tracker.common.error

class BusinessException(
    val code: ErrorCode,
    message: String
) : RuntimeException(message)
