package it.reloia.myhousek.alarm.data.remote

import it.reloia.myhousek.alarm.data.AlarmsRepository
import it.reloia.myhousek.alarm.domain.model.Alarm

class AlarmsRepositoryImpl(
    private val alarmsApiService: AlarmsApiService
) : AlarmsRepository {
    override suspend fun getAlarms(): List<Alarm> {
        println("AlarmsRepositoryImpl.getAlarms")
        return alarmsApiService.getAlarms()
    }

    override suspend fun toggleHarmAlarm(id: String) {
        alarmsApiService.toggleHarmAlarm(id)
    }
}