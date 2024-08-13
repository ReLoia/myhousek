package it.reloia.myhousek

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import it.reloia.myhousek.ui.theme.MyhouseKTheme

data class Page(val name: String, val route: String, val content: @Composable () -> Unit)

class OtherActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val page = intent.getStringExtra("page")
            val pages = listOf(
                Page("Settings", "settings") {
                    Text("Settings")
                },
            )
            val selectedPage = pages.first { it.route == page }

            MyhouseKTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text(selectedPage.name) },
                            navigationIcon = {
                                IconButton(onClick = {
                                    finish()
                                }) {
                                    Icon(
                                        painter = ,
                                        contentDescription =
                                    )
                                }
                            }
                        )
                    }
                ) { padding ->

                }
            }
        }
    }
}
