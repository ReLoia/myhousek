package it.reloia.myhousek.tasks.ui

import androidx.lifecycle.ViewModel
import it.reloia.myhousek.tasks.domain.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TasksViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    fun loadTasks() {
        // Simulate a data load
        // TODO: Replace with your actual data source logic
        val tasks = listOf(
            Task(
                id = "1",
                title = "Task 1",
                description = "Description for Task 1",
                isCompleted = false,
                assignedUsers = emptyList(),
                timestamp = System.currentTimeMillis(),
                author = "Author"
            ),
            Task(
                id = "2",
                title = "Task 2",
                description = "Description for Task 2",
                isCompleted = false,
                assignedUsers = emptyList(),
                timestamp = System.currentTimeMillis(),
                author = "Author"
            ),
            Task(
                id = "3",
                title = "Task 3",
                description = "Description for Task 3",
                isCompleted = false,
                assignedUsers = emptyList(),
                timestamp = System.currentTimeMillis(),
                author = "Author"
            )
        )
        _tasks.value = tasks
    }

    fun addTask(task: Task) {
        // TODO:
        _tasks.value = _tasks.value + task
    }

}