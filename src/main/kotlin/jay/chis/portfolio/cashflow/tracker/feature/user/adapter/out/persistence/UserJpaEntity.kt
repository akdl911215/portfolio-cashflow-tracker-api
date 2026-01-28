package jay.chis.portfolio.cashflow.tracker.feature.user.adapter.out.persistence

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(
    name = "users",
    uniqueConstraints = [
        UniqueConstraint(name = "uq_users_email", columnNames = ["email"])
    ]
)
class UserJpaEntity(
    @Id
    @Column(columnDefinition = "uuid")
    val id: UUID,

    @Column(nullable = false, length = 255)
    val email: String,

    @Column(nullable = false, length = 60)
    val nickname: String,

    @Column(nullable = false, length = 255)
    val password: String
)