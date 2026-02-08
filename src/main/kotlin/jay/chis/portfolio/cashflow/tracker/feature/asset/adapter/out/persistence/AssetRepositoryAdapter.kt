package jay.chis.portfolio.cashflow.tracker.feature.asset.adapter.out.persistence

import jay.chis.portfolio.cashflow.tracker.feature.asset.domain.Asset
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class AssetRepositoryAdapter(
    private val assetJpaRepository: AssetJpaRepository
) {
    fun save(asset: Asset): Asset {
        val saved = assetJpaRepository.save(asset.toEntity())
        return saved.toDomain()
    }

    fun findByIdAndUserId(assetId: UUID, userId: UUID): Asset? =
        assetJpaRepository.findByIdAndUserId(assetId, userId)?.toDomain()

    fun findByUserIdAndSymbol(userId: UUID, symbol: String): Asset? =
        assetJpaRepository.findByUserIdAndSymbol(userId, symbol.uppercase().trim())?.toDomain()

    fun existsByUserIdAndSymbol(userId: UUID, symbol: String): Boolean =
        assetJpaRepository.existsByUserIdAndSymbol(userId, symbol.uppercase().trim())

    fun existsByIdAndUserId(assetId: UUID, userId: UUID): Boolean =
        assetJpaRepository.existsByIdAndUserId(assetId, userId)

    fun findAllByUserId(userId: UUID): List<Asset> =
        assetJpaRepository.findAllByUserId(userId).map { it.toDomain() }

    fun deleteById(assetId: UUID) {
        assetJpaRepository.deleteById(assetId)
    }

    private fun Asset.toEntity(): AssetJpaEntity =
        AssetJpaEntity(
            id = this.id,
            userId = this.userId,
            symbol = this.symbol.uppercase().trim(),
            name = this.name.trim(),
            assetClass = this.assetClass,
            currency = this.currency.uppercase().trim()
        )

    private fun AssetJpaEntity.toDomain(): Asset =
        Asset(
            id = this.id,
            userId = this.userId,
            symbol = this.symbol,
            name = this.name,
            assetClass = this.assetClass,
            currency = this.currency
        )
}
