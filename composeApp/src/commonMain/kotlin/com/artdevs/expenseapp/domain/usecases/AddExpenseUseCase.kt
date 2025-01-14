package com.artdevs.expenseapp.domain.usecases

import com.artdevs.expenseapp.data.repository.ExpenseRepository
import com.artdevs.expenseapp.domain.model.Expense

class AddExpenseUseCase(
    private val expenseRepository: ExpenseRepository
) {
    operator fun invoke(expense: Expense): Unit =
        expenseRepository.addExpense(expense)
}