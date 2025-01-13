package com.artdevs.expenseapp.data.repository

import com.artdevs.expenseapp.domain.model.Expense

interface ExpenseRepository {

    fun getExpenses(): List<Expense>
    fun addExpense(expense: Expense)
    fun removeExpense(expenseId: Int)
}