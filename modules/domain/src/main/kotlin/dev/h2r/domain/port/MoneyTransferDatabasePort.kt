package dev.h2r.domain.port

import dev.h2r.domain.entity.MoneyTransfer

interface MoneyTransferDatabasePort {
    fun findByRequestId(requestId: String): MoneyTransfer?
    fun save(moneyTransfer: MoneyTransfer)
}