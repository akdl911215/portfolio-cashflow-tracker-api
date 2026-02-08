package jay.chis.portfolio.cashflow.tracker.common.error

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val httpStatus: HttpStatus,
    val message: String
) {
    // User
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "Email already exists."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found."),

    // Account
    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "Account not found."),

    // Asset
    ASSET_NOT_FOUND(HttpStatus.NOT_FOUND, "Asset not found."),

    // Common domain errors
    DUPLICATE_RESOURCE(HttpStatus.CONFLICT, "Resource already exists."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "Invalid input."),

    // Validation / Request
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "Invalid request."),
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "Validation failed."),

    // System
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error.");
}

