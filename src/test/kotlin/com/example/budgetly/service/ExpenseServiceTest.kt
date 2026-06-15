package com.example.budgetly.service

import com.example.budgetly.entity.Expense
import com.example.budgetly.integration.nbp.NbpClient
import com.example.budgetly.repository.ExpenseRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import java.math.BigDecimal

class ExpenseServiceTest {

    private val expenseRepository: ExpenseRepository = mock()
    private val nbpClient: NbpClient = mock()
    private val expenseService = ExpenseService(expenseRepository, nbpClient)

    @Test
    fun shouldCalculateTotalPlnExpenses() {
        val expenses = listOf(
            Expense(title = "Pizza", amountPln = BigDecimal("40.00")),
            Expense(title = "Coffee", amountPln = BigDecimal("15.50"))
        )

        val result = expenseService.totalPln(expenses)

        assertEquals(BigDecimal("55.50"), result)
    }

    @Test
    fun shouldFindMaxExpense() {
        val pizza = Expense(title = "Pizza", amountPln = BigDecimal("40.00"))
        val laptop = Expense(title = "Laptop", amountPln = BigDecimal("3000.00"))
        val coffee = Expense(title = "Coffee", amountPln = BigDecimal("15.50"))

        val result = expenseService.maxExpense(listOf(pizza, laptop, coffee))

        assertSame(laptop, result)
    }
}