package it.reloia.myhousek.manage.domain.model

data class Command (
    val id: String,
    val name: String,
    val apiId: String,
    val type: String,
    val value: String,
)