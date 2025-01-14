package com.artdevs.expenseapp.data.datasource.local

import com.artdevs.expenseapp.db.ExpenseDatabase
import com.artdevs.expenseapp.domain.model.Category
import com.artdevs.expenseapp.domain.model.Expense

class ExpenseLocalDataSource(private val db: ExpenseDatabase) {
    private val queries = db.expenseDbQueries

    fun addExpense(description: String, amount: Double, date: String, category: String) {
        queries.transaction {
            queries.addExpense(description, amount, date, category)
        }
    }

    fun getExpenses(): List<Expense> = queries.getExpenses().executeAsList().map {
        Expense(
            id = it.id.toString(),
            description = it.description,
            amount = it.amount,
            date = it.date,
            category = Category.valueOf(it.category)
        )
    }
}