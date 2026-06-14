package com.example.budgetly.service

import com.example.budgetly.entity.Expense
import com.example.budgetly.integration.nbp.NbpClient
import com.example.budgetly.repository.ExpenseRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.YearMonth

@Service
class ExpenseService(
    private val expenseRepository: ExpenseRepository,
    private val nbpClient: NbpClient
) {

    fun findAll(): List<Expense> {
        return expenseRepository.findAll()
    }

    fun findById(id: Long): Expense {
        return expenseRepository.findById(id).orElseThrow()
    }

    fun findByMonth(month: String?): List<Expense> {
        if (month.isNullOrBlank()) {
            return findAll()
        }

        val yearMonth = YearMonth.parse(month)
        val start = yearMonth.atDay(1)
        val end = yearMonth.atEndOfMonth()

        return expenseRepository.findByExpenseDateBetween(start, end)
    }

    fun save(expense: Expense): Expense {
        val rate = nbpClient.getRate(expense.currency)

        expense.exchangeRate = rate
        expense.amountPln = expense.amountOriginal
            .multiply(rate)
            .setScale(2, RoundingMode.HALF_UP)

        return expenseRepository.save(expense)
    }

    fun deleteById(id: Long) {
        expenseRepository.deleteById(id)
    }

    fun totalPln(expenses: List<Expense>): BigDecimal {
        return expenses.fold(BigDecimal.ZERO) { sum, expense ->
            sum + expense.amountPln
        }
    }

    fun maxExpense(expenses: List<Expense>): Expense? {
        return expenses.maxByOrNull { it.amountPln }
    }
}