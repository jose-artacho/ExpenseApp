package com.artdevs.expenseapp.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.artdevs.expenseapp.db.ExpenseDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(ExpenseDatabase.Schema, "expense.db")
    }
}