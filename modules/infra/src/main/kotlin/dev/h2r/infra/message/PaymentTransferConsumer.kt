package dev.h2r.infra.message

import com.fasterxml.jackson.databind.ObjectMapper
import dev.h2r.domain.entity.MoneyTransfer
import dev.h2r.domain.entity.MoneyTransferStatus
import dev.h2r.domain.usecase.MoneyTransferFlow
import dev.h2r.infra.message.MoneyTransferProducer.Companion.PAYMENT_TRANSACTION_QUEUE
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component
import java.util.*

@Component
class PaymentTransferConsumer(
    private val objectMapper: ObjectMapper,
    private val processMoneyTransferFlow: EnumMap<MoneyTransferStatus, MoneyTransferFlow>
) {
    @JmsListener(destination = PAYMENT_TRANSACTION_QUEUE)
    fun receiveMessage(message: String) {
        val moneyTransfer = objectMapper.readValue(message, MoneyTransfer::class.java)
        processMoneyTransferFlow[moneyTransfer.status]?.execute(moneyTransfer)
    }
}