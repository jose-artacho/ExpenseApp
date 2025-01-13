package com.artdevs.expenseapp.domain.model

sealed interface ExpenseUiState {
    data object Empty : ExpenseUiState
    data object Loading : ExpenseUiState
    data class Success(val expenses: List<Expense>, val totalAmount: Double) : ExpenseUiState
}