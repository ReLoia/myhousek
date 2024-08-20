package it.reloia.myhousek.tasks.domain.model

data class CreateTask(
    val title: String,
    val description: String,
    val assignedUsers: List<String>
)
