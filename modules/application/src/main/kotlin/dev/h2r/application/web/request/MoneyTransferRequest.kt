package dev.h2r.application.web.request

import dev.h2r.domain.entity.MoneyTransfer
import dev.h2r.domain.entity.MoneyTransferStatus
import java.math.BigDecimal
import java.time.LocalDateTime

data class MoneyTransferRequest(
    val requestId: String,
    val from: String,
    val to: String,
    val amount: BigDecimal
) {
    fun toPaymentTransfer() = MoneyTransfer(
        requestId = requestId,
        from = from,
        to = to,
        amount = amount,
        requestDate = LocalDateTime.now(),
        status = MoneyTransferStatus.REQUESTED,
        updatedAt = LocalDateTime.now()
    )
}
