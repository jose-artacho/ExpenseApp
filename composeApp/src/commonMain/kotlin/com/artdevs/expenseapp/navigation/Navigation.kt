package com.artdevs.expenseapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.artdevs.expenseapp.domain.model.Expense
import com.artdevs.expenseapp.ui.addexpense.AddExpenseScreenView
import com.artdevs.expenseapp.ui.expensedetail.ExpenseDetailScreenView
import com.artdevs.expenseapp.ui.home.HomeScreenView

@Composable
fun Navigation() {
    val navController = rememberNavController()
    var selectedExpense by remember { mutableStateOf<Expense?>(null) }
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreenView(
                navController,
                onAddExpenseClick = {
                    navController.navigate("addExpense")
                },
                onExpenseClick = {
                    selectedExpense = it
                    navController.navigate("expenseDetail")
                }
            )
        }
        composable("expenseDetail") {
            selectedExpense?.let { expense ->
                ExpenseDetailScreenView(
                    navController = navController,
                    expense = expense
                )
            }
        }
        composable("addExpense") {
            AddExpenseScreenView(
                navController
            )
        }
    }
}