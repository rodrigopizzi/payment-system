package dev.h2r.domain.usecase

import dev.h2r.domain.entity.MoneyTransfer
import dev.h2r.domain.entity.MoneyTransferStatus
import dev.h2r.domain.entity.MoneyTransferStatus.REQUESTED
import dev.h2r.domain.port.MoneyTransferDatabasePort
import dev.h2r.domain.port.MoneyTransferMessagePort
import java.time.LocalDateTime

class ProcessRequestedFlowUseCase(
    private val moneyTransferDatabasePort: MoneyTransferDatabasePort,
    private val moneyTransferMessagePort: MoneyTransferMessagePort
): MoneyTransferFlow {
    override fun execute(moneyTransfer: MoneyTransfer) {
        if (moneyTransfer.status != REQUESTED) {
            throw Exception("Money transfer with requestId ${moneyTransfer.requestId} is not in REQUESTED status")
        }
        moneyTransfer.copy(
            status = MoneyTransferStatus.CREATED,
            updatedAt = LocalDateTime.now()
        ).let {
            if (moneyTransferDatabasePort.findByRequestId(moneyTransfer.requestId) == null) {
                moneyTransferDatabasePort.save(it)
            }
            moneyTransferMessagePort.sendMoneyTransferChanged(it)
        }
    }
}