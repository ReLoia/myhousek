package it.reloia.myhousek.tasks.data.remote

import android.app.Application
import it.reloia.myhousek.R
import it.reloia.myhousek.tasks.data.TasksRepository
import it.reloia.myhousek.tasks.domain.model.CreateTask
import it.reloia.myhousek.tasks.domain.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TasksRepositoryImpl (
    private val tasksApi: TasksApiService,
    private val appContext: Application
) : TasksRepository {

    init {
        println("TasksRepositoryImpl created. App context: ${appContext.getString(R.string.app_name)}")
    }

    override suspend fun getTasks(): List<Task> {
        return withContext(Dispatchers.IO) {
            tasksApi.getTasks()
        }
    }

    override suspend fun addTask(task: CreateTask) {
        withContext(Dispatchers.IO) {
            tasksApi.addTask(task)
        }
    }

    override suspend fun deleteTask(id: String) {
        withContext(Dispatchers.IO) {
            tasksApi.deleteTask(id)
        }
    }

    override suspend fun updateTask(id: String) {
        withContext(Dispatchers.IO) {
            tasksApi.updateTask(id)
        }
    }

    override suspend fun toggleTask(id: String) {
        withContext(Dispatchers.IO) {
            tasksApi.toggleTask(id)
        }
    }
}