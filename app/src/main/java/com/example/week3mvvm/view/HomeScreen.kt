package com.example.week3mvvm.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week3mvvm.model.Task
import com.example.week3mvvm.viewmodel.TaskViewModel




@Composable
fun HomeScreen(
    taskViewModel: TaskViewModel = viewModel()
) {
    val tasks by taskViewModel.tasks.collectAsState()

    var newTaskTitle by remember { mutableStateOf("") }
    var newTaskDueDate by remember { mutableStateOf("")}
    var selectedTask by remember { mutableStateOf<Task?>(null) }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text("Task list", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))

            Text("Tasks count: ${tasks.size}")

            // Input
            OutlinedTextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                label = { Text("Task title") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = newTaskDueDate,
                onValueChange = { newTaskDueDate = it },
                label = { Text("Due date (dd/MM/yyyy)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Add Task Button
            Button(
                onClick = {
                    val newId = (tasks.maxOfOrNull { it.id } ?: 0) + 1
                    taskViewModel.addTask(
                        Task(
                            id = newId,
                            title = newTaskTitle,
                            description = "",
                            priority = 1,
                            dueDate = newTaskDueDate,
                            done = false
                        )
                    )
                    newTaskTitle = ""
                    newTaskDueDate = ""
                }
            ) {
                Text("Add Task")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Buttons for filter / sort / show all
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { taskViewModel.filterByDone(true) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Show Done")
                }
                Button(
                    onClick = { taskViewModel.sortByDueDate() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Sort by Due")
                }
                Button(
                    onClick = { taskViewModel.showAll() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Show All")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // LazyColumn for tasks
            LazyColumn {
                items(tasks) { task ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
                            Checkbox(
                                checked = task.done,
                                onCheckedChange = { taskViewModel.toggleDone(task.id) }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column (
                                modifier = Modifier
                                    .padding(4.dp)
                                    .clickable { selectedTask = task }
                            ) {
                                Text(task.title)
                                Text(
                                    "Due: ${task.dueDate}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                        IconButton(onClick = { taskViewModel.removeTask(task.id) }) {
                            Text("❌")
                        }
                    }
                }
            }
        }
    }
    // Detail dialog
    if (selectedTask != null ) {
        TaskDetailDialog(
            task = selectedTask!!,
            onDismiss = { selectedTask = null },
            onSave = {
                taskViewModel.updateTask(it)
                selectedTask = null
            },
            onDelete = {
                taskViewModel.removeTask(it.id)
                selectedTask = null
            }
        )
    }
}