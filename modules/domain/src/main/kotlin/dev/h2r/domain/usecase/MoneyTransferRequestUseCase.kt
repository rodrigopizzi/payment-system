package dev.h2r.domain.usecase

import dev.h2r.domain.entity.MoneyTransfer
import dev.h2r.domain.entity.MoneyTransferStatus.REQUESTED
import dev.h2r.domain.port.MoneyTransferMessagePort
import java.math.BigDecimal
import java.time.LocalDateTime

class MoneyTransferRequestUseCase(
    private val moneyTransferMessagePort: MoneyTransferMessagePort
) {
    fun execute(moneyTransfer: MoneyTransfer) {
        if (moneyTransfer.requestDate.isBefore(LocalDateTime.now().minusHours(2))) {
            throw Exception("Money transfer request is expired")
        }
        if (moneyTransfer.amount < BigDecimal.ZERO) {
            throw Exception("Money transfer should be positive")
        }
        if (moneyTransfer.from == moneyTransfer.to) {
            throw Exception("Money transfer dont have same from and to account")
        }
        if (moneyTransfer.status != REQUESTED) {
            throw Exception("Money transfer should be in ${REQUESTED.name} status")
        }
        moneyTransferMessagePort.sendMoneyTransferChanged(moneyTransfer)
    }
}