package com.example.budgetly.service

import com.example.budgetly.entity.User
import com.example.budgetly.repository.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

class UserServiceTest {

    private val userRepository: UserRepository = mock()
    private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()
    private val userService = UserService(userRepository, passwordEncoder)

    @Test
    fun shouldRegisterUserWithEncodedPasswordAndDefaultRole() {
        val user = User(
            username = "Patrycja",
            email = "test@test.pl",
            password = "password123"
        )

        whenever(userRepository.save(any())).thenAnswer { it.arguments[0] as User }

        val result = userService.register(user)

        assertEquals("USER", result.role)
        assertNotEquals("password123", result.password)
    }

    @Test
    fun shouldFindUserByEmail() {
        val user = User(
            username = "Patrycja",
            email = "test@test.pl",
            password = "encodedPassword"
        )

        whenever(userRepository.findByEmail("test@test.pl")).thenReturn(user)

        val result = userService.findByEmail("test@test.pl")

        assertEquals("Patrycja", result?.username)
        assertEquals("test@test.pl", result?.email)
    }
}