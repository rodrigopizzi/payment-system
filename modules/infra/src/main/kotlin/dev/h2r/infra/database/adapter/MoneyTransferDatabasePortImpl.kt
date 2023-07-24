package dev.h2r.infra.database.adapter

import dev.h2r.domain.entity.MoneyTransfer
import dev.h2r.domain.port.MoneyTransferDatabasePort
import dev.h2r.infra.database.entity.MoneyTransferEntity
import dev.h2r.infra.database.repository.MoneyTransferRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
class MoneyTransferDatabasePortImpl(
    private val moneyTransferRepository: MoneyTransferRepository
) : MoneyTransferDatabasePort {
    override fun findByRequestId(requestId: String): MoneyTransfer? {
        return moneyTransferRepository.findByRequestId(requestId)?.toDomain()
    }

    override fun save(moneyTransfer: MoneyTransfer) {
        moneyTransferRepository.save(MoneyTransferEntity.fromDomain(moneyTransfer))
    }

}