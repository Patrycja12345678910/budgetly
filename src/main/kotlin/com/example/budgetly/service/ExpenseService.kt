package com.example.budgetly.service

import com.example.budgetly.entity.Expense
import com.example.budgetly.integration.nbp.NbpClient
import com.example.budgetly.repository.ExpenseRepository
import org.springframework.stereotype.Service
import java.math.RoundingMode

@Service
class ExpenseService(
    private val expenseRepository: ExpenseRepository,
    private val nbpClient: NbpClient
) {

    fun findAll(): List<Expense> {
        return expenseRepository.findAll()
    }

    fun save(expense: Expense): Expense {
        val rate = nbpClient.getRate(expense.currency)

        expense.exchangeRate = rate
        expense.amountPln = expense.amountOriginal
            .multiply(rate)
            .setScale(2, RoundingMode.HALF_UP)

        return expenseRepository.save(expense)
    }
}