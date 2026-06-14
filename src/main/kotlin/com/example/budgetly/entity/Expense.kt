package com.example.budgetly.entity


import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
class Expense(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var title: String = "",

    @Column(nullable = false)
    var amountOriginal: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    var currency: String = "PLN",

    @Column(nullable = false)
    var exchangeRate: BigDecimal = BigDecimal.ONE,

    @Column(nullable = false)
    var amountPln: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    var expenseDate: LocalDate = LocalDate.now(),

    @ManyToOne
    @JoinColumn(name = "category_id")
    var category: Category? = null
)