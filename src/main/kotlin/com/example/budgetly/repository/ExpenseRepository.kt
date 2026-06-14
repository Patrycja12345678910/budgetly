package com.example.budgetly.repository

import com.example.budgetly.entity.Expense
import org.springframework.data.jpa.repository.JpaRepository

interface ExpenseRepository : JpaRepository<Expense, Long>