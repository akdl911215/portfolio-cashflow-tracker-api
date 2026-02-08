package jay.chis.portfolio.cashflow.tracker.feature.asset.application

import jay.chis.portfolio.cashflow.tracker.common.error.BusinessException
import jay.chis.portfolio.cashflow.tracker.common.error.ErrorCode
import jay.chis.portfolio.cashflow.tracker.feature.asset.adapter.out.persistence.AssetRepositoryAdapter
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class DeleteAssetUseCase(
    private val assetRepository: AssetRepositoryAdapter
) {
    @Transactional
    fun delete(userId: UUID, assetId: UUID) {
        val exists = assetRepository.existsByIdAndUserId(assetId, userId)
        if (!exists) {
            throw BusinessException(ErrorCode.INVALID_REQUEST, "Asset not found: $assetId")
        }
        assetRepository.deleteById(assetId)
    }
}
