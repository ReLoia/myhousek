package it.reloia.myhousek.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.reloia.myhousek.profile.domain.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    fun loadUserData(userId: String) {
        // Simulate a data load
        viewModelScope.launch {
            // Replace with your actual data source logic
            val userData = User(id = userId, name = "John Doe", profileImageUrl = null)
            _user.value = userData
        }
    }

    fun updateUserProfileImage(newImageUrl: String) {
        _user.value = _user.value?.copy(profileImageUrl = newImageUrl)
    }
}
