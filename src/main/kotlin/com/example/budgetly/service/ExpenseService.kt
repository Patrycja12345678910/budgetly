package com.example.budgetly.service


import com.example.budgetly.entity.Expense
import com.example.budgetly.repository.ExpenseRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class ExpenseService(
    private val expenseRepository: ExpenseRepository
) {

    fun findAll(): List<Expense> {
        return expenseRepository.findAll()
    }

    fun save(expense: Expense): Expense {
        if (expense.currency == "PLN") {
            expense.exchangeRate = BigDecimal.ONE
            expense.amountPln = expense.amountOriginal
        }

        return expenseRepository.save(expense)
    }
}