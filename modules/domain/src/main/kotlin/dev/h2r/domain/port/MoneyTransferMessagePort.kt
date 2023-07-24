package dev.h2r.domain.port

import dev.h2r.domain.entity.MoneyTransfer

interface MoneyTransferMessagePort {

    fun sendMoneyTransferChanged(moneyTransfer: MoneyTransfer)

}