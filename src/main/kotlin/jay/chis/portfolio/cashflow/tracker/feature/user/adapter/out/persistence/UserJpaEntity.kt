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
    var id: UUID = UUID.randomUUID(),

    @Column(nullable = false, length = 255)
    var email: String = "",

    @Column(nullable = false, length = 60)
    var nickname: String = "",

    @Column(nullable = false, length = 255)
    var password: String = ""
)
