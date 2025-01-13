package com.artdevs.expenseapp.domain.usecases

import com.artdevs.expenseapp.data.repository.ExpenseRepository
import com.artdevs.expenseapp.domain.model.Expense

class GetExpensesUseCase(
    private val expenseRepository: ExpenseRepository
) {
    suspend operator fun invoke(): List<Expense> =
        expenseRepository.getExpenses()
}