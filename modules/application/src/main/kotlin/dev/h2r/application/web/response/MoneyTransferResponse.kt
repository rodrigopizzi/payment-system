package dev.h2r.application.web.response

import java.time.LocalDateTime

data class MoneyTransferResponse(
    val requestId: String,
    val requestDate: LocalDateTime
)
