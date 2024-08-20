package it.reloia.myhousek.profile.data.remote

import android.app.Application
import it.reloia.myhousek.profile.data.ProfileRepository
import it.reloia.myhousek.profile.domain.model.User

class RemoteProfileRepositoryImpl (
    private val apiService: ProfileApiService,
    private val appContext: Application
) : ProfileRepository {
    override suspend fun getUserProfile(userId: String): User? {
        TODO("Not yet implemented")
    }

    override suspend fun login(username: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun register(username: String, password: String): Boolean {
        TODO("Not yet implemented")
    }
}