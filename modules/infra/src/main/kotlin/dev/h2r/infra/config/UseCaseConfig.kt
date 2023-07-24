package dev.h2r.infra.config

import dev.h2r.domain.entity.MoneyTransferStatus
import dev.h2r.domain.entity.MoneyTransferStatus.*
import dev.h2r.domain.port.MoneyTransferDatabasePort
import dev.h2r.domain.port.MoneyTransferMessagePort
import dev.h2r.domain.usecase.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.EnumMap

@Configuration
class UseCaseConfig {

    @Bean
    fun paymentTransferRequestUseCase(moneyTransferMessagePort: MoneyTransferMessagePort) =
        MoneyTransferRequestUseCase(moneyTransferMessagePort)

    @Bean
    fun processBalanceLockedFlowUseCase() = ProcessBalanceLockedFlowUseCase()

    @Bean
    fun processCreatedFlowUseCase() = ProcessCreatedFlowUseCase()

    @Bean
    fun processFraudCheckedFlowUseCase() = ProcessFraudCheckedFlowUseCase()

    @Bean
    fun processRequestedFlowUseCase(
        moneyTransferDatabasePort: MoneyTransferDatabasePort,
        moneyTransferMessagePort: MoneyTransferMessagePort
    ) = ProcessRequestedFlowUseCase(moneyTransferDatabasePort, moneyTransferMessagePort)

    @Bean
    fun processTransferAcceptedFlowUseCase() = ProcessTransferAcceptedFlowUseCase()

    @Bean
    fun processTransferFinishedFlowUseCase() = ProcessTransferFinishedFlowUseCase()

    @Bean
    fun processTransferRejectedFlowUseCase() = ProcessTransferRejectedFlowUseCase()

    @Bean
    fun processTransferRequestedFlowUseCase() = ProcessTransferRequestedFlowUseCase()

    @Bean
    fun processTransferFailedFlowUseCase() = ProcessTransferFailedFlowUseCase()

    @Bean
    fun processMoneyTransferFlow(
        processRequestedFlowUseCase: ProcessRequestedFlowUseCase,
        processCreatedFlowUseCase: ProcessCreatedFlowUseCase,
        processFraudCheckedFlowUseCase: ProcessFraudCheckedFlowUseCase,
        processBalanceLockedFlowUseCase: ProcessBalanceLockedFlowUseCase,
        processTransferRequestedFlowUseCase: ProcessTransferRequestedFlowUseCase,
        processTransferAcceptedFlowUseCase: ProcessTransferAcceptedFlowUseCase,
        processTransferRejectedFlowUseCase: ProcessTransferRejectedFlowUseCase,
        processTransferFinishedFlowUseCase: ProcessTransferFinishedFlowUseCase,
        processTransferFailedFlowUseCase: ProcessTransferFailedFlowUseCase
    ) = EnumMap<MoneyTransferStatus, MoneyTransferFlow>(MoneyTransferStatus::class.java)
        .apply {
            this[REQUESTED] = processRequestedFlowUseCase
            this[CREATED] = processCreatedFlowUseCase
            this[FRAUD_CHECKED] = processFraudCheckedFlowUseCase
            this[BALANCE_LOCKED] = processBalanceLockedFlowUseCase
            this[TRANSFER_REQUESTED] = processTransferRequestedFlowUseCase
            this[TRANSFER_ACCEPTED] = processTransferAcceptedFlowUseCase
            this[TRANSFER_REJECTED] = processTransferRejectedFlowUseCase
            this[FINISHED] = processTransferFinishedFlowUseCase
            this[FAILED] = processTransferFailedFlowUseCase
        }

}