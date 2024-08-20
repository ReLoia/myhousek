package it.reloia.myhousek.tasks.data

import it.reloia.myhousek.tasks.domain.model.CreateTask
import it.reloia.myhousek.tasks.domain.model.Task

interface TasksRepository {
    suspend fun getTasks(): List<Task>
    suspend fun addTask(task: CreateTask)
    suspend fun deleteTask(id: String)
    suspend fun updateTask(id: String)
    suspend fun toggleTask(id: String)
}