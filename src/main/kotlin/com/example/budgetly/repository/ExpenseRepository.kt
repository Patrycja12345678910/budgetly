package com.example.budgetly.repository

import com.example.budgetly.entity.Expense
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface ExpenseRepository : JpaRepository<Expense, Long> {

    fun findByExpenseDateBetween(start: LocalDate, end: LocalDate): List<Expense>
}