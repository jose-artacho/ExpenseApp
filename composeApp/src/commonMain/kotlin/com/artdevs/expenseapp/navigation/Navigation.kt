package com.artdevs.expenseapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.artdevs.expenseapp.ui.addexpense.AddExpenseScreenView
import com.artdevs.expenseapp.ui.home.HomeScreenView

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreenView(
                onAddExpenseClick = {
                    navController.navigate("addExpense")
                },
                onExpenseClick = {
                    navController.navigate("expenseDetail")
                }
            )
        }
        composable(
            "expenseDetail/{expenseId}",
            arguments = listOf(navArgument("expenseId") { type = NavType.LongType })
        ) {
            val expenseId = it.arguments?.getLong("expenseId")
        }
        composable("addExpense") {
            AddExpenseScreenView(
                navController
            )
        }
    }
}