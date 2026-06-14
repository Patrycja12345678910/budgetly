package com.example.budgetly.controller

import com.example.budgetly.entity.Expense
import com.example.budgetly.service.CategoryService
import com.example.budgetly.service.ExpenseService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/expenses")
class ExpenseController(
    private val expenseService: ExpenseService,
    private val categoryService: CategoryService
) {

    @GetMapping
    fun list(
        @RequestParam(required = false) month: String?,
        model: Model
    ): String {
        val expenses = expenseService.findByMonth(month)

        model.addAttribute("expenses", expenses)
        model.addAttribute("selectedMonth", month)
        model.addAttribute("totalPln", expenseService.totalPln(expenses))
        model.addAttribute("maxExpense", expenseService.maxExpense(expenses))

        return "expenses/list"
    }

    @GetMapping("/new")
    fun newForm(model: Model): String {
        model.addAttribute("expense", Expense())
        model.addAttribute("categories", categoryService.findAll())
        return "expenses/form"
    }

    @PostMapping
    fun save(@ModelAttribute expense: Expense): String {
        expenseService.save(expense)
        return "redirect:/expenses"
    }
}