package com.example.budgetly.service

import com.example.budgetly.entity.User
import com.example.budgetly.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun register(user: User): User {
        user.password = passwordEncoder.encode(user.password) ?: ""
        user.role = "USER"
        return userRepository.save(user)
    }

    fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }
}