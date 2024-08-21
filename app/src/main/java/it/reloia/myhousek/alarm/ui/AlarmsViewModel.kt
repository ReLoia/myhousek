package it.reloia.myhousek.alarm.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.reloia.myhousek.alarm.data.AlarmsRepository
import it.reloia.myhousek.alarm.domain.model.Alarm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlarmsViewModel(
    private val repository: AlarmsRepository
) : ViewModel() {
    private val _alarms = MutableStateFlow<List<Alarm>>(emptyList())
    val alarms: StateFlow<List<Alarm>> = _alarms

    fun loadAlarms() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _alarms.value = repository.getAlarms()
            } catch (e: Exception) {
                e.printStackTrace()
                _alarms.value = emptyList()
            }
        }
    }

    fun toggleHarmAlarm(id: String) {
        viewModelScope.launch {
            repository.toggleHarmAlarm(id)
        }
    }

}