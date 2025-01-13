package com.artdevs.expenseapp.domain.model

sealed interface ExpenseUiState {
    data object Loading : ExpenseUiState
    data class Success(val expenses: List<Expense>, val totalAmount: Double) : ExpenseUiState
    data class Error(val message: String) : ExpenseUiState
}