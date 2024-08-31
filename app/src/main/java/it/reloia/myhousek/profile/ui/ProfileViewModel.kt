package it.reloia.myhousek.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.reloia.myhousek.profile.data.ProfileRepository
import it.reloia.myhousek.profile.domain.model.TokenModel
import it.reloia.myhousek.profile.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: ProfileRepository
) : ViewModel() {
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    fun loadUserProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            _user.value = repository.getUserProfile()
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val token: TokenModel = repository.login(username, password)
            _user.value = repository.getUserProfile(token.access_token)
        }
    }

    fun register(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.register(username, password)
        }
    }
}
