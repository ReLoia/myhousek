package it.reloia.myhousek.tasks.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.reloia.myhousek.tasks.data.TasksRepository
import it.reloia.myhousek.tasks.domain.model.CreateTask
import it.reloia.myhousek.tasks.domain.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val repository: TasksRepository
) : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    fun loadTasks() {
        // Simulate a data load
        // TODO: Replace with your actual data source logic
        viewModelScope.launch {
            _tasks.value = repository.getTasks()
        }
    }

    fun addTask(
        title: String,
        description: String,
        assignedUsers: List<String>
    ) {
        println("Adding task with title: $title, description: $description, assignedUsers: $assignedUsers")
        viewModelScope.launch {
            print(repository.addTask(CreateTask(
                title = title,
                description = description,
                assignedUsers = assignedUsers
            )))
        }

    }

}