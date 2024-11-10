package it.reloia.myhousek.profile.data.remote

import it.reloia.myhousek.profile.domain.model.TokenModel
import it.reloia.myhousek.profile.domain.model.User
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ProfileApiService {
    @GET("user")
    suspend fun getUserProfile(): User

    @GET("profile")
    suspend fun getCurrentUser(@Header("Authorization") authToken: String): User

    @FormUrlEncoded
    @POST("login")
    suspend fun login(@Field("username") username: String, @Field("password") password: String): TokenModel

    @FormUrlEncoded
    @POST("register")
    suspend fun register(@Field("username") username: String, @Field("password") password: String): TokenModel
}