package com.example.budgetly

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BudgetlyApplication

fun main(args: Array<String>) {
	runApplication<BudgetlyApplication>(*args)
}
