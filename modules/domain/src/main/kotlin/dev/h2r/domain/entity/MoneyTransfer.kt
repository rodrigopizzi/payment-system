package dev.h2r.domain.entity

import java.math.BigDecimal
import java.time.LocalDateTime

data class MoneyTransfer(
    val requestId: String,
    val from: String,
    val to: String,
    val amount: BigDecimal,
    val requestDate: LocalDateTime,
    val status: MoneyTransferStatus,
    val updatedAt: LocalDateTime
)

enum class MoneyTransferStatus {
    REQUESTED,
    CREATED,
    FRAUD_CHECKED,
    BALANCE_LOCKED,
    TRANSFER_REQUESTED,
    TRANSFER_ACCEPTED,
    TRANSFER_REJECTED,
    FINISHED,
    FAILED
}
