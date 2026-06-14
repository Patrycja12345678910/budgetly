package com.example.budgetly.integration.nbp


import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import java.math.BigDecimal

@Component
class NbpClient {

    private val restClient = RestClient.create()

    fun getRate(currency: String): BigDecimal {
        if (currency == "PLN") {
            return BigDecimal.ONE
        }

        val url = "https://api.nbp.pl/api/exchangerates/rates/A/$currency/?format=json"

        val response = restClient
            .get()
            .uri(url)
            .retrieve()
            .body(NbpRateResponse::class.java)

        return response?.rates?.firstOrNull()?.mid
            ?: throw IllegalStateException("Nie udało się pobrać kursu waluty: $currency")
    }
}