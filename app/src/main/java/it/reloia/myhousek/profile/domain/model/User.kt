package it.reloia.myhousek.profile.domain.model

data class User(
    val name: String,
    /**
     * The ids of the tasks that the user has done
     */
    val tasksDone: List<String>,
    var accessToken: String
)
