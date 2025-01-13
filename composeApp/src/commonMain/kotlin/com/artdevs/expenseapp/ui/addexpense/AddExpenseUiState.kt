package com.artdevs.expenseapp.ui.addexpense

import com.artdevs.expenseapp.domain.model.Expense

sealed interface AddExpenseUiState {
    data object Loading : AddExpenseUiState
    data object Success : AddExpenseUiState
    data class Error(val message: String) : AddExpenseUiState
}