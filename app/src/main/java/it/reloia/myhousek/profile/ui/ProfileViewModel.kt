package it.reloia.myhousek.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.reloia.myhousek.profile.data.ProfileRepository
import it.reloia.myhousek.profile.domain.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: ProfileRepository
) : ViewModel() {
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    fun loadUserProfile(userId: String) {
        viewModelScope.launch() {
            _user.value = repository.getUserProfile(userId)
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            repository.login(username, password)
        }
    }

    fun register(username: String, password: String) {
        viewModelScope.launch {
            repository.register(username, password)
        }
    }
}
