package it.reloia.myhousek.tasks.domain.model

data class Task(
    val id: String,
    val title: String,
    val description: String,
    var isCompleted: Boolean,
    val assignedUsers: List<String>,
    val timestamp: Long,
    val author: String
)
