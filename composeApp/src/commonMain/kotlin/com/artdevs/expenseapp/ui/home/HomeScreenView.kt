package com.artdevs.expenseapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.artdevs.expenseapp.data.ExpenseDataManager
import com.artdevs.expenseapp.domain.model.Expense
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreenView(onAddExpenseClick: () -> Unit, onExpenseClick: (expense: Expense) -> Unit) {

    val viewModel = koinViewModel<HomeScreenViewModel>()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Header
            HeaderSection()

            // Card Section
            CardSection(title = "Total Expense", amount = "100 €")

            // Latest Entries
            LatestEntriesSection(ExpenseDataManager.fakeExpenseLit) {
                onExpenseClick(it)
            }
        }

        FloatingActionButton(
            onClick = { onAddExpenseClick() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp),
            containerColor = Color.Blue,
            contentColor = Color.White
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add New Entry")
        }
    }
}

@Composable
fun HeaderSection() {

    Row(
        modifier = Modifier.fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Overview",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun CardSection(title: String, amount: String) {
    Column(
        modifier = Modifier
            .padding(vertical = 24.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            elevation = cardElevation(4.dp),
            colors = cardColors(Color.Blue),
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountBalanceWallet,
                    contentDescription = "Total expense icon",
                    tint = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = title,
                    fontSize = 16.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = amount,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun LatestEntriesSection(expenses: List<Expense>, onExpenseClick: (expense: Expense) -> Unit) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .fillMaxSize()
            .background(Color.White),
    ) {
        Text(
            modifier = Modifier.padding(24.dp),
            text = "Latest Entries",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        expenses.forEach { expense ->
            EntryItem(expense = expense) {
                onExpenseClick(it)
            }
        }.takeIf { expenses.isEmpty() }?.run {
            Text(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                text = "No expenses yet",
                fontSize = 18.sp,
                color = Color.LightGray,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun EntryItem(expense: Expense, onClick: (expense: Expense) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(expense)
            }
            .padding(horizontal = 24.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color.LightGray,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = expense.category.icon,
                contentDescription = expense.category.name,
                tint = Color.Black,
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = expense.category.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(text = expense.date, fontSize = 14.sp, color = Color.Gray)
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = expense.amount.toString() + " €",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}