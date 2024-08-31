package it.reloia.myhousek.profile.data.remote

import android.app.Application
import it.reloia.myhousek.profile.data.ProfileRepository
import it.reloia.myhousek.profile.domain.model.TokenModel
import it.reloia.myhousek.profile.domain.model.User
import it.reloia.myhousek.profile.domain.model.UserLogin

class RemoteProfileRepositoryImpl (
    private val apiService: ProfileApiService,
    private val appContext: Application
) : ProfileRepository {
    override suspend fun getUserProfile(): User {
        val user = apiService.getUserProfile()
        return user
    }

    override suspend fun getUserProfile(token: String): User {
        val user = apiService.getCurrentUser(TokenModel(token, "bearer"))
        return user
    }

    override suspend fun login(username: String, password: String): TokenModel {
        val token = apiService.login(UserLogin(username, password))
        return token
    }

    override suspend fun register(username: String, password: String): Boolean {
        TODO("Not yet implemented")
    }
}