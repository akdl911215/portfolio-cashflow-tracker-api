package jay.chis.portfolio.cashflow.tracker.feature.asset.application

import jay.chis.portfolio.cashflow.tracker.common.error.BusinessException
import jay.chis.portfolio.cashflow.tracker.common.error.ErrorCode
import jay.chis.portfolio.cashflow.tracker.feature.asset.adapter.out.persistence.AssetRepositoryAdapter
import jay.chis.portfolio.cashflow.tracker.feature.asset.domain.Asset
import jay.chis.portfolio.cashflow.tracker.feature.asset.domain.AssetClass
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class UpdateAssetUseCase(
    private val assetRepository: AssetRepositoryAdapter
) {
    @Transactional
    fun update(userId: UUID, assetId: UUID, name: String, assetClass: AssetClass, currency: String): Asset {
        val asset = assetRepository.findByIdAndUserId(assetId, userId)
            ?: throw BusinessException(ErrorCode.INVALID_REQUEST, "Asset not found: $assetId")

        val updated = asset.copy(
            name = name.trim(),
            assetClass = assetClass,
            currency = currency.uppercase().trim()
        )

        return assetRepository.save(updated)
    }
}
