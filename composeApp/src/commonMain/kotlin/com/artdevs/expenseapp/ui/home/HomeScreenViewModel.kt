package com.artdevs.expenseapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artdevs.expenseapp.domain.usecases.GetExpensesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val getExpensesUseCase: GetExpensesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ExpenseUiState>(ExpenseUiState.Idle)
    val uiState = _uiState.asStateFlow()

    init {
        getAllExpenses()
    }

    fun getAllExpenses() {
        viewModelScope.launch {
            val expenses = getExpensesUseCase()
            _uiState.update {
                val totalAmount = expenses.sumOf { it.amount }
                ExpenseUiState.Success(expenses, totalAmount)
            }.runCatching {
                ExpenseUiState.Error("Error fetching expenses")
            }
        }
    }
}