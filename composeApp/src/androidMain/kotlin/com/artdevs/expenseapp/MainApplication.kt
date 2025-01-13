package com.artdevs.expenseapp

import android.app.Application
import com.artdevs.expenseapp.data.DatabaseDriverFactory
import com.artdevs.expenseapp.db.ExpenseDatabase
import com.artdevs.expenseapp.di.appModule
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule(ExpenseDatabase.invoke(DatabaseDriverFactory(this@MainApplication).createDriver())))
        }
    }
}