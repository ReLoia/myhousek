package it.reloia.myhousek

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.reloia.myhousek.ui.theme.MyhouseKTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            MyhouseKTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    /*color = MaterialTheme.colorScheme.background,*/
                ) {
                    Scaffold(
                        topBar = {
                            // TopAppBar(title = { Text("MyHouse") })
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
                                    /*HomeScreen()*/
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
