package jay.chis.portfolio.cashflow.tracker.feature.asset.domain

import java.util.UUID

data class Asset(
    val id: UUID,
    val userId: UUID,
    val symbol: String,   // like "AAPL", "SPY", "BTC"
    val name: String,     // like "Apple Inc."
    val assetClass: AssetClass,
    val currency: String  // like "USD", "KRW"
)