package dev.h2r.infra.database.repository

import dev.h2r.infra.database.entity.MoneyTransferEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MoneyTransferRepository : MongoRepository<MoneyTransferEntity, String> {
    fun findByRequestId(requestId: String): MoneyTransferEntity?
}