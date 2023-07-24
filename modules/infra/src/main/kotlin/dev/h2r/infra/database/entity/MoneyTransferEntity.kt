package dev.h2r.infra.database.entity

import dev.h2r.domain.entity.MoneyTransfer
import dev.h2r.domain.entity.MoneyTransferStatus
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDateTime

@Document("moneyTransfer")
data class MoneyTransferEntity(
    @Id
    val requestId: String,
    val from: String,
    val to: String,
    val amount: BigDecimal,
    val requestDate: LocalDateTime,
    val status: MoneyTransferStatus,
    val updatedAt: LocalDateTime
) {
    fun toDomain() = MoneyTransfer(
        requestId = requestId,
        from = from,
        to = to,
        amount = amount,
        requestDate = requestDate,
        status = status,
        updatedAt = updatedAt
    )

    companion object {
        fun fromDomain(moneyTransfer: MoneyTransfer) = MoneyTransferEntity(
            requestId = moneyTransfer.requestId,
            from = moneyTransfer.from,
            to = moneyTransfer.to,
            amount = moneyTransfer.amount,
            requestDate = moneyTransfer.requestDate,
            status = moneyTransfer.status,
            updatedAt = moneyTransfer.updatedAt
        )
    }
}
