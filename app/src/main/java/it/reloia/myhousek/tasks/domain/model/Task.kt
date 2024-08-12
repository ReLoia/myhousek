package it.reloia.myhousek.tasks.domain.model

data class Task(
    val id: String,
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    val assignedUsers: List<String>,
    val timestamp: Long
)
