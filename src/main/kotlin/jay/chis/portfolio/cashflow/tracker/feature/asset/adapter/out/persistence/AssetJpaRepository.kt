package jay.chis.portfolio.cashflow.tracker.feature.asset.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface AssetJpaRepository : JpaRepository<AssetJpaEntity, UUID> {
    fun existsByUserIdAndSymbol(userId: UUID, symbol: String): Boolean
    fun findByUserIdAndSymbol(userId: UUID, symbol: String): AssetJpaEntity?
    fun findAllByUserId(userId: UUID): List<AssetJpaEntity>
    fun findByIdAndUserId(id: UUID, userId: UUID): AssetJpaEntity?
    fun existsByIdAndUserId(id: UUID, userId: UUID): Boolean
}
