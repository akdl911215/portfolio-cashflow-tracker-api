package jay.chis.portfolio.cashflow.tracker.feature.account.adapter.out.persistence

import jakarta.persistence.*
import jay.chis.portfolio.cashflow.tracker.feature.account.domain.AccountType
import java.util.UUID

@Entity
@Table(
    name = "accounts",
    indexes = [
        Index(name = "idx_accounts_user_id", columnList = "user_id")
    ]
)
class AccountJpaEntity(

    @Id
    @Column(columnDefinition = "uuid")
    var id: UUID = UUID.randomUUID(),

    @Column(name = "user_id", columnDefinition = "uuid", nullable = false)
    var userId: UUID = UUID.randomUUID(),

    @Column(nullable = false, length = 100)
    var name: String = "",

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    var type: AccountType = AccountType.BROKERAGE
)
