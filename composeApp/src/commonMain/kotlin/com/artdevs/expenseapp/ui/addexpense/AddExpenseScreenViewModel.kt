package com.artdevs.expenseapp.ui.addexpense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artdevs.expenseapp.domain.model.Expense
import com.artdevs.expenseapp.domain.usecases.AddExpenseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddExpenseScreenViewModel(
    private val addExpenseUseCase: AddExpenseUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AddExpenseUiState>(AddExpenseUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            addExpenseUseCase(expense = expense)
            _uiState.update {
                AddExpenseUiState.Success
            }.runCatching {
                AddExpenseUiState.Error("Error fetching expenses")
            }
        }
    }
}