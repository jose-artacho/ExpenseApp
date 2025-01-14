package com.artdevs.expenseapp

import androidx.compose.ui.window.ComposeUIViewController
import com.artdevs.expenseapp.data.DatabaseDriverFactory
import com.artdevs.expenseapp.db.ExpenseDatabase
import com.artdevs.expenseapp.di.appModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController { App() }

fun initKoin() {
    startKoin {
        modules(appModule(ExpenseDatabase.invoke(DatabaseDriverFactory().createDriver())))
    }.koin
}