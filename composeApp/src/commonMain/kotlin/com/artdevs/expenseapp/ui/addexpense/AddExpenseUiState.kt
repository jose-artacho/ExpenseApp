package com.artdevs.expenseapp.ui.addexpense

sealed interface AddExpenseUiState {
    data object Idle : AddExpenseUiState
    data object Success : AddExpenseUiState
    data class Error(val message: String) : AddExpenseUiState
}