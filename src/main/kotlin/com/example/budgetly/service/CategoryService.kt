package com.example.budgetly.service

import com.example.budgetly.entity.Category
import com.example.budgetly.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {

    fun findAll(): List<Category> {
        return categoryRepository.findAll()
    }

    fun save(category: Category): Category {
        return categoryRepository.save(category)
    }
}