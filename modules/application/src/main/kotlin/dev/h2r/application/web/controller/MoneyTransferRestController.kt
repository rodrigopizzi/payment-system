package dev.h2r.application.web.controller

import dev.h2r.application.web.request.MoneyTransferRequest
import dev.h2r.application.web.response.MoneyTransferResponse
import dev.h2r.domain.usecase.MoneyTransferRequestUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/payment")
class MoneyTransferRestController(
    private val moneyTransferRequestUseCase: MoneyTransferRequestUseCase
) {
    @PostMapping("/transfer")
    fun transfer(@RequestBody request: MoneyTransferRequest): ResponseEntity<MoneyTransferResponse> {
        request.toPaymentTransfer().also {
            moneyTransferRequestUseCase.execute(it)
            return ResponseEntity.accepted().body(MoneyTransferResponse(request.requestId, it.requestDate))
        }
    }

}