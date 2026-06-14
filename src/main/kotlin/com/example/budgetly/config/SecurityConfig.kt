package com.example.budgetly.config

import com.example.budgetly.service.CustomUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val customUserDetailsService: CustomUserDetailsService
) {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .userDetailsService(customUserDetailsService)
            .authorizeHttpRequests {
                it.requestMatchers("/login", "/register").permitAll()
                    .anyRequest().authenticated()
            }
            .formLogin {
                it.loginPage("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/", true)
                    .permitAll()
            }
            .logout {
                it.logoutSuccessUrl("/login")
                    .permitAll()
            }
            .csrf { it.disable() }

        return http.build()
    }
}