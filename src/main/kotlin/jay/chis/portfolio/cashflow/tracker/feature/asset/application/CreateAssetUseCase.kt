package jay.chis.portfolio.cashflow.tracker.feature.asset.application

import jay.chis.portfolio.cashflow.tracker.common.error.BusinessException
import jay.chis.portfolio.cashflow.tracker.common.error.ErrorCode
import jay.chis.portfolio.cashflow.tracker.feature.asset.adapter.out.persistence.AssetRepositoryAdapter
import jay.chis.portfolio.cashflow.tracker.feature.asset.domain.Asset
import jay.chis.portfolio.cashflow.tracker.feature.asset.domain.AssetClass
import jay.chis.portfolio.cashflow.tracker.feature.user.adapter.out.persistence.UserJpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class CreateAssetUseCase(
    private val assetRepository: AssetRepositoryAdapter,
    private val userRepository: UserJpaRepository
) {
    @Transactional
    fun create(userId: UUID, symbol: String, name: String, assetClass: AssetClass, currency: String): Asset {
        if (!userRepository.existsById(userId)) {
            throw BusinessException(ErrorCode.USER_NOT_FOUND, "User not found: $userId")
        }

        val cleanSymbol = symbol.uppercase().trim()
        if (cleanSymbol.isBlank()) {
            throw BusinessException(ErrorCode.INVALID_REQUEST, "Symbol is blank")
        }

        if (assetRepository.existsByUserIdAndSymbol(userId, cleanSymbol)) {
            throw BusinessException(ErrorCode.INVALID_REQUEST, "Asset already exists for user: $cleanSymbol")
        }

        val asset = Asset(
            id = UUID.randomUUID(),
            userId = userId,
            symbol = cleanSymbol,
            name = name.trim(),
            assetClass = assetClass,
            currency = currency.uppercase().trim()
        )

        return assetRepository.save(asset)
    }
}
