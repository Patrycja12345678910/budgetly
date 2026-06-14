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

    fun findById(id: Long): Category {
        return categoryRepository.findById(id).orElseThrow()
    }

    fun save(category: Category): Category {
        return categoryRepository.save(category)
    }

    fun deleteById(id: Long) {
        categoryRepository.deleteById(id)
    }
}