package com.example.budgetly.controller


import com.example.budgetly.entity.Expense
import com.example.budgetly.service.CategoryService
import com.example.budgetly.service.ExpenseService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/expenses")
class ExpenseController(
    private val expenseService: ExpenseService,
    private val categoryService: CategoryService
) {

    @GetMapping
    fun list(model: Model): String {
        model.addAttribute("expenses", expenseService.findAll())
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