package com.example.budgetly.controller

import com.example.budgetly.entity.Category
import com.example.budgetly.service.CategoryService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/categories")
class CategoryController(
    private val categoryService: CategoryService
) {

    @GetMapping
    fun list(model: Model): String {
        model.addAttribute("categories", categoryService.findAll())
        return "categories/list"
    }

    @GetMapping("/new")
    fun newForm(model: Model): String {
        model.addAttribute("category", Category())
        return "categories/form"
    }

    @PostMapping
    fun save(@ModelAttribute category: Category): String {
        categoryService.save(category)
        return "redirect:/categories"
    }
}