package com.artdevs.expenseapp.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.material.icons.filled.Workspaces
import androidx.compose.ui.graphics.vector.ImageVector

data class Expense(
    val description: String = "",
    val amount: Double = 0.0,
    val date: String = "",
    val category: Category = Category.OTHER
)

enum class Category(val icon: ImageVector) {
    RESTAURANT(Icons.Default.Fastfood),
    TRAVEL(Icons.Default.TravelExplore),
    SUPERMARKET(Icons.Default.ShoppingCart),
    SHOPPING(Icons.Default.ShoppingBag),
    ENTERTAINMENT(Icons.Default.SportsEsports),
    INVOICE(Icons.Default.Receipt),
    OTHER(Icons.Default.Workspaces)
}
