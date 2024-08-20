package it.reloia.myhousek.tasks.data.remote

import it.reloia.myhousek.tasks.data.TasksRepository
import it.reloia.myhousek.tasks.domain.model.CreateTask
import it.reloia.myhousek.tasks.domain.model.Task

class RemoteTasksRepositoryImpl (
    private val tasksApi: TasksApiService
) : TasksRepository {
    override suspend fun getTasks(): List<Task> {
        return tasksApi.getTasks()
    }

    override suspend fun addTask(task: CreateTask) {
        tasksApi.addTask(task)
    }

    override suspend fun deleteTask(id: String) {
//        tasksApi.deleteTask(id)
        tasksApi.deleteTask(id)
    }

    override suspend fun updateTask(id: String) {
//        tasksApi.updateTask(id)
        tasksApi.updateTask(id)
    }

    override suspend fun toggleTask(id: String) {
//        tasksApi.toggleTask(id)
        tasksApi.toggleTask(id)
    }
}