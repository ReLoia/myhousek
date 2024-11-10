package it.reloia.myhousek.profile.data.remote

import android.app.Application
import it.reloia.myhousek.profile.data.ProfileRepository
import it.reloia.myhousek.profile.domain.model.TokenModel
import it.reloia.myhousek.profile.domain.model.User

class RemoteProfileRepositoryImpl (
    private val apiService: ProfileApiService,
    private val appContext: Application
) : ProfileRepository {
    override suspend fun getUserProfile(): User {
        val user = apiService.getUserProfile()
        return user
    }

    override suspend fun getUserProfile(token: String): User {
        val user = apiService.getCurrentUser("Bearer $token")
        user.accessToken = token
        return user
    }

    override suspend fun login(username: String, password: String): TokenModel {
        val token = apiService.login(username, password)
        return token
    }

    override suspend fun register(username: String, password: String): TokenModel {
        val token = apiService.register(username, password)
        return token
    }
}