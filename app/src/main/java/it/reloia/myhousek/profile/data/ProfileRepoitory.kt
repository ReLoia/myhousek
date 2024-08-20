package it.reloia.myhousek.profile.data

import it.reloia.myhousek.profile.domain.model.User

interface ProfileRepository {
    suspend fun getUserProfile(userId: String): User?
    suspend fun login(username: String, password: String): Boolean
    suspend fun register(username: String, password: String): Boolean
}
