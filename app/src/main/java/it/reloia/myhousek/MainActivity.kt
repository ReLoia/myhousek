package it.reloia.myhousek

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.reloia.myhousek.profile.ui.ProfileAppBar
import it.reloia.myhousek.profile.ui.ProfileViewModel
import it.reloia.myhousek.ui.theme.MyhouseKTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            /* TODO: replace with actual image */
            val profileImage = null;
            val profileViewModel = ProfileViewModel()

            MyhouseKTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    /*color = MaterialTheme.colorScheme.background,*/
                ) {
                    Scaffold(
                        topBar = {
                            NavHost(
                                navController = navController,
                                startDestination = "home",
                            ) {
                                composable("home") {
                                    ProfileAppBar(userViewModel = profileViewModel, navController = navController)
                                }
                                composable("tasks") {
                                    /*TodoScreen()*/
                                }
                                composable("manage") {
                                    /*ManageScreen()*/
                                }
                            }
                            /*TopAppBar(
                                title = { Text(stringResource(R.string.home)) },
                                actions = {

                                }
                            )*/
                        },
                        bottomBar = {
                            // BottomAppBar()
                        },

                        ) { padding ->
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(padding),

                            ) {
                            NavHost(
                                navController = navController,
                                startDestination = "home",
                            ) {
                                composable("home") {

                                }
                                composable("tasks") {
                                    /*TodoScreen()*/
                                }
                                composable("manage") {
                                    /*ManageScreen()*/
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
