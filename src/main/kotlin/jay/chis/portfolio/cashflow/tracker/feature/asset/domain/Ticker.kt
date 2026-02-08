package jay.chis.portfolio.cashflow.tracker.feature.asset.domain

data class Ticker(
    val market: String,   // e.g. NASDAQ, KRX, NYSE
    val symbol: String    // e.g. AAPL, SPY, 005930
) {
    fun code(): String = "${market.uppercase()}:${symbol.uppercase()}"
}
