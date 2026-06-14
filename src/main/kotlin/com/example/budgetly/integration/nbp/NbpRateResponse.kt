package com.example.budgetly.integration.nbp

import java.math.BigDecimal

data class NbpRateResponse(
    val table: String,
    val currency: String,
    val code: String,
    val rates: List<NbpRate>
)

data class NbpRate(
    val no: String,
    val effectiveDate: String,
    val mid: BigDecimal
)