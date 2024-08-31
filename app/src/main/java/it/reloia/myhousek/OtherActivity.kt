package it.reloia.myhousek

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.glance.LocalContext
import it.reloia.myhousek.profile.data.remote.ProfileApiService
import it.reloia.myhousek.profile.data.remote.RemoteProfileRepositoryImpl
import it.reloia.myhousek.profile.ui.LoginScreen
import it.reloia.myhousek.profile.ui.ProfileAppBar
import it.reloia.myhousek.profile.ui.ProfileScreen
import it.reloia.myhousek.profile.ui.ProfileViewModel
import it.reloia.myhousek.settings.ui.SettingsAppBar
import it.reloia.myhousek.settings.ui.SettingsScreen
import it.reloia.myhousek.ui.theme.MyhouseKTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

data class Page(
    val name: String,
    val route: String,
    val content: @Composable () -> Unit,
    val topBar: @Composable() (() -> Unit)? = null
)

class OtherActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val profileViewModel: ProfileViewModel = ProfileViewModel(
                RemoteProfileRepositoryImpl(
                    Retrofit.Builder()
                        .baseUrl("https://myhousek-api.onrender.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(ProfileApiService::class.java),
                    this.application
                )
            )

            val page = intent.getStringExtra("page")
            val pages = listOf(
                Page(
                    stringResource(R.string.settings),
                    "settings",
                    content = { SettingsScreen() },
                    topBar = { SettingsAppBar() }),
                Page(
                    stringResource(R.string.profile),
                    "profile",
                    content = { ProfileScreen(profileViewModel = profileViewModel) },
                    topBar = { ProfileAppBar(profileViewModel = profileViewModel) }),
                Page(
                    "Login",
                    "login",
                    content = { LoginScreen(profileViewModel = profileViewModel) },
                    topBar = {}
                ),
                Page(
                    "test",
                    "test",
                    content = {
                        Text("Test")
                    }
                )
            )
            println("Page: $page")
            val selectedPage = pages.first { it.route == page }

            MyhouseKTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        if (selectedPage.topBar != null) {
                            selectedPage.topBar.invoke()
                        } else {
                            TopAppBar(
                                title = { Text(selectedPage.name) },
                                navigationIcon = {
                                    IconButton(onClick = {
                                        finish()
                                    }) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = "Back"
                                        )
                                    }
                                }
                            )
                        }
                    }
                ) { padding ->
                    Surface(
                        modifier = Modifier.padding(padding)
                    ) {
                        selectedPage.content()
                    }
                }
            }
        }
    }
}
