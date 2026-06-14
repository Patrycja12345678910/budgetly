package com.example.budgetly.service

import com.example.budgetly.entity.Expense
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class ExpenseServiceTest {

    @Test
    fun shouldCalculateTotalPlnExpenses() {
        val expenses = listOf(
            Expense(title = "Pizza", amountPln = BigDecimal("40.00")),
            Expense(title = "Coffee", amountPln = BigDecimal("15.50"))
        )

        val result = expenses.fold(BigDecimal.ZERO) { sum, expense ->
            sum + expense.amountPln
        }

        assertEquals(BigDecimal("55.50"), result)
    }

    @Test
    fun shouldFindMaxExpense() {
        val pizza = Expense(title = "Pizza", amountPln = BigDecimal("40.00"))
        val laptop = Expense(title = "Laptop", amountPln = BigDecimal("3000.00"))
        val coffee = Expense(title = "Coffee", amountPln = BigDecimal("15.50"))

        val result = listOf(pizza, laptop, coffee).maxByOrNull { it.amountPln }

        assertSame(laptop, result)
    }
}