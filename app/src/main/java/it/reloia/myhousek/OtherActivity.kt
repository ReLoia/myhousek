package it.reloia.myhousek

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import it.reloia.myhousek.profile.ui.ProfileAppBar
import it.reloia.myhousek.profile.ui.ProfileScreen
import it.reloia.myhousek.profile.ui.ProfileViewModel
import it.reloia.myhousek.settings.ui.SettingsScreen
import it.reloia.myhousek.ui.theme.MyhouseKTheme

data class Page(
    val name: String,
    val route: String,
    val content: @Composable () -> Unit,
    val topBar: @Composable () -> Unit = {}
)

class OtherActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var profileViewModel: ProfileViewModel = ProfileViewModel()

            val page = intent.getStringExtra("page")
            val pages = listOf(
                Page("Settings", "settings", content = { SettingsScreen() }),
                Page(
                    "Profile",
                    "profile",
                    content = { ProfileScreen(profileViewModel = profileViewModel) },
                    topBar = { ProfileAppBar(profileViewModel = profileViewModel) })
            )
            val selectedPage = pages.first { it.route == page }

            MyhouseKTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        when (page) {
                            "profile" -> {
                                selectedPage.topBar()
                            }
                            else -> {
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
