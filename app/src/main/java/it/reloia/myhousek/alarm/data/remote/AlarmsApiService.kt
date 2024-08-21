package it.reloia.myhousek.alarm.data.remote

import it.reloia.myhousek.alarm.domain.model.Alarm
import retrofit2.http.GET

interface AlarmsApiService {
    @GET("alarms")
    suspend fun getAlarms(): List<Alarm>

    @GET("alarms/{id}/toggle")
    suspend fun toggleHarmAlarm(id: String): Boolean
}