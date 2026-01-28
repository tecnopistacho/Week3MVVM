package com.example.week3mvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.week3mvvm.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import java.time.format.DateTimeFormatter




class TaskViewModel : ViewModel() {
    private val _tasks = MutableStateFlow(MockTasks.toList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val allTasks = MockTasks.toMutableList()

    fun addTask(task: Task) {
        allTasks.add(task)
        _tasks.value = allTasks.toList()
    }

    fun toggleDone(id: Int) {
        allTasks.replaceAll { if (it.id == id) it.copy(done = !it.done) else it }
        _tasks.value = allTasks.toList()
    }

    fun removeTask(id: Int) {
        allTasks.removeAll { it.id == id }
        _tasks.value = allTasks.toList()
    }

    fun updateTask(updatedTask: Task) {
        allTasks.replaceAll { if (it.id == updatedTask.id) updatedTask else it }
        _tasks.value = allTasks.toList()
    }

    fun filterByDone(done: Boolean) {
        _tasks.value = allTasks.filter { it.done == done }
    }

    fun sortByDueDate() {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        _tasks.value = _tasks.value.sortedBy { task ->
            LocalDate.parse(task.dueDate, formatter)
        }
    }

    fun showAll() {
        _tasks.value = allTasks.toList()
    }
}
