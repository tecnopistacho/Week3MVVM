Week 3 – MVVM Architechture
Overview
This project is an extension of the Week 2 Task Manager app, implementing MVVM architecture using Jetpack Compose and StateFlow.
MVVM separates the app into three layers:
1. Model – Represents the data (Task class in model package)
2. ViewModel – Manages UI state, contains all business logic (TaskViewModel)
3. View – Composables that display data and interact with the user (HomeScreen, TaskDetailDialog)
This structure makes the app modular, maintainable, and testable, and ensures the UI updates automatically when data changes.

Project structure: 
com.example.week3mvvm
├── model
│   └── Task.kt           # Task data class
├── view
│   ├── HomeScreen.kt     # Main task list UI
│   └── TaskDetailDialog.kt # Dialog for editing/deleting tasks
├── viewmodel
│   └── TaskViewModel.kt  # State management & task logic


Features
- Add, edit, and delete tasks
- Mark tasks as done/undone
- Filter by done tasks
- Sort tasks by due date
- Show all tasks
- Reactive UI using StateFlow and collectAsState() in Compose
- Detail dialog for task editing and deletion

How State Management Works
The ViewModel exposes a StateFlow<List<Task>> called tasks.
Any changes to tasks (add, edit, delete, toggle done, filter, sort) update the _tasks MutableStateFlow.
The UI collects the flow using collectAsState(), so it automatically re-renders when the task list changes.
Example in HomeScreen.kt:
´´´val tasks by taskViewModel.tasks.collectAsState()´´´
This ensures that the UI is always in sync with the data, without manually refreshing.

Why MVVM is useful in Compose
- Separation of concerns: UI code doesn’t handle business logic.
- Reactivity: Compose works seamlessly with StateFlow or LiveData.
- Testability: ViewModel logic can be tested independently of the UI.
- Maintainability: Easy to extend (e.g., adding new filters or sorting).

Screenshots
1. HomeScreen
Displays a list of tasks
Add task form
Buttons for filter, sort, and show all

2. TaskDetailDialog
Opens when clicking a task
Edit task title or due date
Save changes or delete task

Tech Stack
- Kotlin + Jetpack Compose
- MVVM architecture
- StateFlow for reactive state
- Android Studio + Gradle
- Java Time (LocalDate, DateTimeFormatter) for due date handling

  Video Demo:
  
