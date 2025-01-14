package com.artdevs.expenseapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.artdevs.expenseapp.navigation.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigation()
    }
}