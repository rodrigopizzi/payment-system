package dev.h2r.domain.usecase

import dev.h2r.domain.entity.MoneyTransfer

interface MoneyTransferFlow {
    fun execute(moneyTransfer: MoneyTransfer)
}