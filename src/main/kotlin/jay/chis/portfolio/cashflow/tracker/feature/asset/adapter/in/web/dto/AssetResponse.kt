package jay.chis.portfolio.cashflow.tracker.feature.asset.adapter.`in`.web.dto

import jay.chis.portfolio.cashflow.tracker.feature.asset.domain.AssetClass
import java.util.UUID

data class AssetResponse(
    val id: UUID,
    val userId: UUID,
    val symbol: String,
    val name: String,
    val assetClass: AssetClass,
    val currency: String
)
