# ExpenseApp

## Overview
ExpenseApp is a simple and efficient expense management application designed for both Android and iOS platforms. The app allows users to add and view expenses with essential details like description, amount, category, and date. This project demonstrates a clean architecture approach combined with multiplatform support

## Features
- List of expenses, and show total expense amount
- Add expenses with description, amount, category, and date.
- View expense details in a non-editable format.
- Multiplatform support: Android and iOS.

## Architecture
The project follows the **MVVM (Model-View-ViewModel)** architecture pattern to separate concerns and ensure testability and maintainability. Below is an overview of the structure:

### Layers
1. **Data Layer**
  - **datasource.local**: Handles local data storage using SQLDelight for both Android and iOS.
    - `ExpenseLocalDataSource`: Provides data access methods for the app.
  - **repository**: Acts as the single source of truth by interacting with the data sources.
    - `ExpenseRepository`: Interface defining the data operations.
    - `ExpenseRepositoryImpl`: Implementation of `ExpenseRepository`, interacts with `ExpenseLocalDataSource`.

2. **Domain Layer**
  - **model**: Contains the core business models.
    - `Expense`: Represents an expense entity.
  - **usecases**: Encapsulates business logic for specific features
    - `AddExpenseUseCase`: Handles the logic for adding a new expense.
    - `GetExpensesUseCase`: Fetches expenses for display

3. **UI Layer**
  - **addexpense**:
    - `AddExpenseScreenView`: Composable screen to add a new expense
    - `AddExpenseScreenViewModel`: Manages the state and logic for the add expense screen
    - `AddExpenseUiState`: Represents the UI state of the add expense screen
  - **expensedetail**:
    - `ExpenseDetailScreenView`: Composable screen to view expense details in a read-only format
  - **home**:
    - `HomeScreenView`: Displays the list of expenses
    - `HomeScreenViewModel`: Manages the state and logic for the home screen
    - `ExpenseUiState`: Represents the UI state of the home screen

4. **Dependency Injection**
  - **di**:
    - `AppModule`: Configures the dependency graph using Koin

5. **Navigation**
  - Centralized in `Navigation.kt` for handling app-wide navigation between screens

## Technologies Used
- **Kotlin Multiplatform**: To enable shared code between Android and iOS.
- **SQLDelight**: For local database operations.
- **Koin**: For dependency injection.
- **Material 3**: For UI components and styling.

## Video Demonstration
- Android: [Add video link here]
- iOS: [Add video link here]

## How to Run
1. Clone the repository.
2. Open the project in Android Studio (for Android) or Xcode (for iOS).
3. Sync dependencies.
4. Run the app on an emulator or physical device.

## Future Improvements
- Add support for editing and deleting expenses.
- Integrate cloud synchronization for data backup.
- Implement advanced filtering and reporting features.
- Add more tests

