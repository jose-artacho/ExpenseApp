package com.artdevs.expenseapp.data

import com.artdevs.expenseapp.domain.model.Category
import com.artdevs.expenseapp.domain.model.Expense

object ExpenseDataManager {
    private var expenseId = 1L

    val fakeExpenseLit = mutableListOf(
        Expense(
            amount = 20.0,
            date = "15 Mar 2024",
            category = Category.TRAVEL,
            description = "travel expense"
        ),
        Expense(
            amount = 16.5,
            date = "16 Mar 2024",
            category = Category.INVOICE,
            description = "travel expense"
        ),
        Expense(
            amount = 15.9,
            date = "17 Mar 2024",
            category = Category.SHOPPING,
            description = "travel expense"
        ),
        Expense(
            amount = 13.0 ,
            date = "19 Mar 2024",
            category = Category.RESTAURANT,
            description = "travel expense"
        )
    )

    fun addExpense(expense: Expense) {
        fakeExpenseLit.add(expense)
    }
}