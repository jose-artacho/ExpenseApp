package com.artdevs.expenseapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreenView() {
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
            LatestEntriesSection()
        }

        FloatingActionButton(
            onClick = { /* TODO: Add new entry action */ },
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
                .align(Alignment.CenterHorizontally)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountBox,
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
fun LatestEntriesSection() {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 32.dp),
    ) {
        Text(
            text = "Latest Entries",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        EntryItem(
            icon = "Food",
            date = "20 Feb 2024",
            amount = "+ $20 + Vat 0.5%"
        )
        EntryItem(
            icon = "Uber",
            date = "13 Mar 2024",
            amount = "- $18 + Vat 0.8%"
        )
        EntryItem(
            icon = "Shopping",
            date = "11 Mar 2024",
            amount = "- $400 + Vat 0.12%"
        )
    }
}

@Composable
fun EntryItem(icon: String, date: String, amount: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color.LightGray,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = icon, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(text = date, fontSize = 14.sp, color = Color.Gray)
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(text = amount, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        }
    }
}