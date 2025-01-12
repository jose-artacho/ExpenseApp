package com.artdevs.expenseapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.artdevs.expenseapp.ui.home.HomeScreenView
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        HomeScreenView()
    }
}