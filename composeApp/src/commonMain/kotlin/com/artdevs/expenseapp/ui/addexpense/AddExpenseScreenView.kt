package com.artdevs.expenseapp.ui.addexpense

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.artdevs.expenseapp.domain.model.Category
import com.artdevs.expenseapp.domain.model.Expense
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseScreenView(navController: NavController = rememberNavController()) {

    val viewModel = koinViewModel<AddExpenseScreenViewModel>()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    var description by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(Category.OTHER) }
    var selectedDate by remember {
        mutableStateOf(
            Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        )
    }

    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var isCategorySheetOpen by remember { mutableStateOf(false) }
    var isDatePickerOpen by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Add Expense",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            DescriptionField(description) { description = it }
            AmountField(amount) { amount = it }
            CategorySelector(selectedCategory, { isCategorySheetOpen = true })
            DateSelector(selectedDate, { isDatePickerOpen = true })
            SaveButton {
                val expense = Expense(
                    description = description,
                    amount = amount.toDouble(),
                    category = selectedCategory,
                    date = selectedDate.toString()
                )
                viewModel.addExpense(expense)
            }

            when (state) {
                is AddExpenseUiState.Loading -> {
                    // Show loading state
                }

                is AddExpenseUiState.Success -> {
                    navController.popBackStack()
                }

                is AddExpenseUiState.Error -> {
                    // Show error state
                }
            }
        }

        if (isCategorySheetOpen) {
            CategoryBottomSheet(
                categories = Category.entries.toList(),
                onCategorySelected = {
                    selectedCategory = it
                    isCategorySheetOpen = false
                },
                onDismiss = { isCategorySheetOpen = false }
            )
        }

        if (isDatePickerOpen) {
            DatePickerDialog(
                onDismissRequest = { isDatePickerOpen = false },
                onDateSelected = {
                    selectedDate = it
                    isDatePickerOpen = false
                }
            )
        }
    }
}

@Composable
fun DescriptionField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Description") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun AmountField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Amount") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun CategorySelector(selectedCategory: Category, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Category: $selectedCategory", fontSize = 16.sp)
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Select Category")
        }
    }
}

@Composable
fun DateSelector(selectedDate: LocalDate, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Date: $selectedDate", fontSize = 16.sp)
            Icon(imageVector = Icons.Default.DateRange, contentDescription = "Select Date")
        }
    }
}

@Composable
fun SaveButton(onSaveClick: () -> Unit) {
    Button(
        onClick = { onSaveClick() },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Save Expense")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryBottomSheet(
    categories: List<Category>,
    onCategorySelected: (Category) -> Unit,
    onDismiss: () -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = bottomSheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Select Category",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            categories.forEach { category ->
                ListItem(
                    headlineContent = { Text(text = category.name) },
                    leadingContent = {
                        Icon(imageVector = category.icon, contentDescription = category.name)
                    },
                    modifier = Modifier.clickable { onCategorySelected(category) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(onDismissRequest: () -> Unit, onDateSelected: (LocalDate) -> Unit) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = {
                val selectedDateMillis = datePickerState.selectedDateMillis ?: return@TextButton
                val selectedDate =
                    Instant.fromEpochMilliseconds(selectedDateMillis)
                        .toLocalDateTime(TimeZone.currentSystemDefault()).date
                onDateSelected(selectedDate)
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}
