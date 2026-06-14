package com.example.budgetly.service

import com.example.budgetly.entity.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UserServiceTest {

    @Test
    fun shouldCreateUserWithDefaultRole() {
        val user = User(
            username = "Patrycja",
            email = "test@test.pl",
            password = "password123"
        )

        assertEquals("USER", user.role)
    }

    @Test
    fun shouldEncodePassword() {
        val passwordEncoder = BCryptPasswordEncoder()
        val rawPassword = "password123"

        val encodedPassword = passwordEncoder.encode(rawPassword)

        assertNotEquals(rawPassword, encodedPassword)
    }
}