package it.reloia.myhousek.alarm.data

import it.reloia.myhousek.alarm.domain.model.Alarm

interface AlarmsRepository {
    suspend fun getAlarms(): List<Alarm>
    suspend fun toggleHarmAlarm(id: String)
}