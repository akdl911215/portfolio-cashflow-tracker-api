package jay.chis.portfolio.cashflow.tracker.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(
    info = Info(
        title = "Portfolio Cashflow Tracker API",
        version = "v0.1",
        description = "Personal portfolio and cashflow tracking API"
    )
)
class OpenApiConfig