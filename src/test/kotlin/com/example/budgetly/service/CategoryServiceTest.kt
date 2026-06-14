package com.example.budgetly.service

import com.example.budgetly.entity.Category
import com.example.budgetly.repository.CategoryRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CategoryServiceTest {

    @Test
    fun shouldReturnAllCategories() {
        val categories = listOf(
            Category(id = 1L, name = "Jedzenie"),
            Category(id = 2L, name = "Transport")
        )

        assertEquals(2, categories.size)
        assertEquals("Jedzenie", categories[0].name)
        assertEquals("Transport", categories[1].name)
    }

    @Test
    fun shouldCreateCategoryObject() {
        val category = Category(name = "Zakupy")

        assertEquals("Zakupy", category.name)
    }
}