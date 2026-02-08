package jay.chis.portfolio.cashflow.tracker.feature.asset.adapter.`in`.web.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import jay.chis.portfolio.cashflow.tracker.feature.asset.domain.AssetClass

data class CreateAssetRequest(
    @field:NotBlank
    @field:Size(max = 30)
    val symbol: String,

    @field:NotBlank
    @field:Size(max = 200)
    val name: String,

    val assetClass: AssetClass,

    @field:NotBlank
    @field:Size(max = 10)
    val currency: String = "USD"
)