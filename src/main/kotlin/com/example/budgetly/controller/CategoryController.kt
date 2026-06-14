package com.example.budgetly.controller

import com.example.budgetly.entity.Category
import com.example.budgetly.service.CategoryService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

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

    @GetMapping("/{id}/edit")
    fun editForm(@PathVariable id: Long, model: Model): String {
        model.addAttribute("category", categoryService.findById(id))
        return "categories/form"
    }

    @PostMapping
    fun save(@ModelAttribute category: Category): String {
        categoryService.save(category)
        return "redirect:/categories"
    }

    @PostMapping("/{id}/delete")
    fun delete(@PathVariable id: Long): String {
        categoryService.deleteById(id)
        return "redirect:/categories"
    }
}