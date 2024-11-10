package it.reloia.myhousek.profile.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.reloia.myhousek.profile.data.ProfileRepository
import it.reloia.myhousek.profile.domain.model.TokenModel
import it.reloia.myhousek.profile.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ProfileViewModel(
    private val repository: ProfileRepository
) : ViewModel() {
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun loadUserProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _user.value = repository.getUserProfile()
            } catch (e: HttpException) {
                if (e.code() == 404) {
                    _errorMessage.value = "User profile not found."
                } else {
                    _errorMessage.value = "An error occurred: ${e.message()}"
                }
            } catch (e: IOException) {
                _errorMessage.value = "Network error. Please check your connection."
            }
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val token: TokenModel = repository.login(username, password)
                _user.value = repository.getUserProfile(token.access_token)
            } catch (e: HttpException) {
                _errorMessage.value = "Login failed: ${e.message()}"
            } catch (e: IOException) {
                _errorMessage.value = "Network error. Please check your connection."
            }
        }
    }


    fun register(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val token: TokenModel = repository.register(username, password)
                _user.value = repository.getUserProfile(token.access_token)
            } catch (e: HttpException) {
                _errorMessage.value = "Registration failed: ${e.message()}"
            } catch (e: IOException) {
                _errorMessage.value = "Network error. Please check your connection."
            }
        }
    }

    fun logout(ctx: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            ctx.getSharedPreferences("it.reloia.myhousek.PREFERENCE_FILE_KEY", Context.MODE_PRIVATE).edit().clear().apply()
            _user.value = null
        }
    }
}
