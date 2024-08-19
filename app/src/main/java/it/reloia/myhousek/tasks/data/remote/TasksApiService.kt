package it.reloia.myhousek.tasks.data.remote

import it.reloia.myhousek.tasks.domain.model.Task
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.DELETE

interface TasksApiService {
    @GET("tasks")
    suspend fun getTasks(): List<Task>

    @POST("tasks")
    suspend fun addTask(@Body task: Task): Boolean

    @PUT("tasks/{id}")
    suspend fun updateTask(@Path("id") id: String): Boolean

    @PUT("tasks/{id}/toggle")
    suspend fun toggleTask(@Path("id") id: String): Boolean

    @DELETE("tasks/{id}")
    suspend fun deleteTask(@Path("id") id: String): Boolean
}
