package com.example.budgetly.service

import com.example.budgetly.entity.Category
import com.example.budgetly.repository.CategoryRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.Optional

class CategoryServiceTest {

    private val categoryRepository: CategoryRepository = mock()
    private val categoryService = CategoryService(categoryRepository)

    @Test
    fun shouldReturnAllCategories() {
        val categories = listOf(
            Category(id = 1L, name = "Jedzenie"),
            Category(id = 2L, name = "Transport")
        )

        whenever(categoryRepository.findAll()).thenReturn(categories)

        val result = categoryService.findAll()

        assertEquals(2, result.size)
        assertEquals("Jedzenie", result[0].name)
        assertEquals("Transport", result[1].name)
    }

    @Test
    fun shouldFindCategoryById() {
        val category = Category(id = 1L, name = "Jedzenie")

        whenever(categoryRepository.findById(1L)).thenReturn(Optional.of(category))

        val result = categoryService.findById(1L)

        assertEquals(1L, result.id)
        assertEquals("Jedzenie", result.name)
    }

    @Test
    fun shouldSaveCategory() {
        val category = Category(name = "Zakupy")

        whenever(categoryRepository.save(category)).thenReturn(category)

        val result = categoryService.save(category)

        assertEquals("Zakupy", result.name)
    }

    @Test
    fun shouldDeleteCategoryById() {
        categoryService.deleteById(1L)

        verify(categoryRepository).deleteById(1L)
    }
}