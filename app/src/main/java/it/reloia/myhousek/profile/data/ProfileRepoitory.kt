package it.reloia.myhousek.profile.data

import it.reloia.myhousek.profile.domain.model.TokenModel
import it.reloia.myhousek.profile.domain.model.User

interface ProfileRepository {
    suspend fun getUserProfile(): User?
    suspend fun getUserProfile(token: String): User?
    suspend fun login(username: String, password: String): TokenModel
    suspend fun register(username: String, password: String): Boolean
}
