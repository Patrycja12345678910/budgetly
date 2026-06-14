package com.example.budgetly.controller

import com.example.budgetly.entity.User
import com.example.budgetly.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class AuthController(
    private val userService: UserService
) {

    @GetMapping("/register")
    fun registerForm(model: Model): String {
        model.addAttribute("user", User())
        return "auth/register"
    }

    @PostMapping("/register")
    fun register(@ModelAttribute user: User): String {
        userService.register(user)
        return "redirect:/login"
    }

    @GetMapping("/login")
    fun loginPage(): String {
        return "auth/login"
    }
}