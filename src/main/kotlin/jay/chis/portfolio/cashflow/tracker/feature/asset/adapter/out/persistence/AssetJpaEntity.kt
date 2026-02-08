package jay.chis.portfolio.cashflow.tracker.feature.asset.adapter.out.persistence

import jakarta.persistence.*
import jay.chis.portfolio.cashflow.tracker.feature.asset.domain.AssetClass
import java.util.UUID

@Entity
@Table(
    name = "assets",
    uniqueConstraints = [
        UniqueConstraint(
            name = "uq_assets_user_symbol",
            columnNames = ["user_id", "symbol"]
        )
    ],
    indexes = [
        Index(name = "idx_assets_user_id", columnList = "user_id"),
        Index(name = "idx_assets_user_class", columnList = "user_id, asset_class")
    ]
)
class AssetJpaEntity(

    @Id
    @Column(columnDefinition = "uuid")
    var id: UUID = UUID.randomUUID(),

    @Column(name = "user_id", columnDefinition = "uuid", nullable = false)
    var userId: UUID = UUID.randomUUID(),

    @Column(nullable = false, length = 30)
    var symbol: String = "",

    @Column(nullable = false, length = 200)
    var name: String = "",

    @Enumerated(EnumType.STRING)
    @Column(name = "asset_class", nullable = false, length = 30)
    var assetClass: AssetClass = AssetClass.STOCK,

    @Column(nullable = false, length = 10)
    var currency: String = "USD"
)
