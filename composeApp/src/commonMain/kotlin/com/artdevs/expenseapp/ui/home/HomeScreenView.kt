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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.artdevs.expenseapp.domain.model.Expense
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenView(navController: NavController = rememberNavController(), onAddExpenseClick: () -> Unit, onExpenseClick: (expense: Expense) -> Unit) {

    val viewModel = koinViewModel<HomeScreenViewModel>()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(navController.currentBackStackEntry) {
        navController.currentBackStackEntryFlow.collect {
            viewModel.getAllExpenses()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Home",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when (state) {
                is ExpenseUiState.Success -> {

                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.LightGray),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {

                            CardSection(
                                title = "Total Expense",
                                amount = (state as ExpenseUiState.Success).totalAmount.toString() + " €"
                            )
                            LatestEntriesSection((state as ExpenseUiState.Success).expenses) {
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
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add New Entry"
                            )
                        }
                    }
                }

                is ExpenseUiState.Error -> {
                    // Show Error state
                }

                else -> {}
            }
        }
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

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (expenses.isEmpty()) {
                item {
                    Text(
                        text = "No expenses yet",
                        fontSize = 18.sp,
                        color = Color.LightGray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 24.dp)
                    )
                }
            } else {
                items(expenses) { expense ->
                    EntryItem(expense = expense) {
                        onExpenseClick(it)
                    }
                }
            }
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