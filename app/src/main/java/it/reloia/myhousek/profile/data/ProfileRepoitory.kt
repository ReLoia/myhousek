package it.reloia.myhousek.profile.data

import it.reloia.myhousek.profile.domain.model.User

interface ProfileRepository {
    suspend fun getUserProfile(userId: String): User?
    suspend fun updateUserProfile(user: User): Boolean
}
