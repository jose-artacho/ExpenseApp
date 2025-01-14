package com.artdevs.expenseapp.ui.home

import com.artdevs.expenseapp.domain.model.Expense

sealed interface ExpenseUiState {
    data object Idle : ExpenseUiState
    data class Success(val expenses: List<Expense>, val totalAmount: Double) : ExpenseUiState
    data class Error(val message: String) : ExpenseUiState
}