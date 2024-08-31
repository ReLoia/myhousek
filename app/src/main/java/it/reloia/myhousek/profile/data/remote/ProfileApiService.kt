package it.reloia.myhousek.profile.data.remote

import it.reloia.myhousek.profile.domain.model.TokenModel
import it.reloia.myhousek.profile.domain.model.User
import it.reloia.myhousek.profile.domain.model.UserLogin
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProfileApiService {

    @GET("user")
    suspend fun getUserProfile(): User

    @POST("login")
    suspend fun login(@Body userLogin: UserLogin): TokenModel

    @POST("register")
    suspend fun register(): Boolean
}