package com.artdevs.expenseapp.data.repository

import com.artdevs.expenseapp.data.datasource.local.ExpenseLocalDataSource
import com.artdevs.expenseapp.db.ExpenseDatabase
import com.artdevs.expenseapp.domain.model.Expense

class ExpenseRepositoryImpl(
    private val localDataSource: ExpenseLocalDataSource
) : ExpenseRepository {
    override fun getExpenses(): List<Expense> {
        return localDataSource.getExpenses()
    }

    override fun addExpense(expense: Expense) {
        localDataSource.addExpense(
            expense.description,
            expense.amount,
            expense.date,
            expense.category.toString()
        )
    }

    override fun removeExpense(expenseId: Int) {
        localDataSource.removeExpense(expenseId)
    }
}