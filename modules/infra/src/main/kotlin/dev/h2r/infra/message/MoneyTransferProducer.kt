package dev.h2r.infra.message

import com.fasterxml.jackson.databind.ObjectMapper
import dev.h2r.domain.entity.MoneyTransfer
import dev.h2r.domain.port.MoneyTransferMessagePort
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Component

@Component
class MoneyTransferProducer(
    private val jmsTemplate: JmsTemplate,
    private val objectMapper: ObjectMapper
) : MoneyTransferMessagePort {
    override fun sendMoneyTransferChanged(moneyTransfer: MoneyTransfer) {
        objectMapper.writeValueAsString(moneyTransfer).also {
            jmsTemplate.convertAndSend(PAYMENT_TRANSACTION_QUEUE, it)
        }
    }

    companion object {
        const val PAYMENT_TRANSACTION_QUEUE = "money-transfer"
    }
}