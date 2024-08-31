package it.reloia.myhousek.profile.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.reloia.myhousek.profile.data.ProfileRepository
import it.reloia.myhousek.profile.domain.model.TokenModel
import it.reloia.myhousek.profile.domain.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: ProfileRepository,
    private val appContext: Application
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
            val token: TokenModel = repository.login(username, password)
            appContext.getSharedPreferences("myhousek", 0).edit().putString("token", token.access_token).apply()
        }
    }

    fun register(username: String, password: String) {
        viewModelScope.launch {
            repository.register(username, password)
        }
    }
}
