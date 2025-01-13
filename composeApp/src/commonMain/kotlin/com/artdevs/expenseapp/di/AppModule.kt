package com.artdevs.expenseapp.di

import com.artdevs.expenseapp.data.datasource.local.ExpenseLocalDataSource
import com.artdevs.expenseapp.data.repository.ExpenseRepository
import com.artdevs.expenseapp.data.repository.ExpenseRepositoryImpl
import com.artdevs.expenseapp.db.ExpenseDatabase
import com.artdevs.expenseapp.domain.usecases.AddExpenseUseCase
import com.artdevs.expenseapp.domain.usecases.GetExpensesUseCase
import com.artdevs.expenseapp.domain.usecases.RemoveExpenseUseCase
import com.artdevs.expenseapp.ui.home.HomeScreenViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun appModule(db: ExpenseDatabase) = module {
    single { ExpenseLocalDataSource(db) }
    single<ExpenseRepository> { ExpenseRepositoryImpl(get()) }

    single { AddExpenseUseCase(get()) }
    single { GetExpensesUseCase(get()) }
    single { RemoveExpenseUseCase(get()) }

    viewModelOf(::HomeScreenViewModel)
}