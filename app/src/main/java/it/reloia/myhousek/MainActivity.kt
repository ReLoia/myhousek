package it.reloia.myhousek

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.sharp.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.reloia.myhousek.home.ui.HomePage
import it.reloia.myhousek.profile.ui.ProfileAppBar
import it.reloia.myhousek.profile.ui.ProfileViewModel
import it.reloia.myhousek.tasks.ui.TasksAppBar
import it.reloia.myhousek.tasks.ui.TasksPage
import it.reloia.myhousek.tasks.ui.TasksViewModel
import it.reloia.myhousek.ui.theme.MyhouseKTheme

data class NavBarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String = title,
)

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
            /*tasks*/
            val tasksViewModel = TasksViewModel()

            val navigationItems = listOf(
                NavBarItem("Tasks", Icons.AutoMirrored.Filled.List, Icons.AutoMirrored.Sharp.List),
                NavBarItem("Home", Icons.Default.Home, Icons.Outlined.Home),
                NavBarItem(
                    "Manage",
                    ImageVector.vectorResource(id = R.drawable.baseline_tune_24),
                    ImageVector.vectorResource(id = R.drawable.baseline_tune_24)
                ),
            )
            var selectedIndex by rememberSaveable {
                mutableIntStateOf(1)
            }

            MyhouseKTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    /*color = MaterialTheme.colorScheme.background,*/
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            when (navigationItems[selectedIndex].title) {
                                "Home" -> {
                                    ProfileAppBar(
                                        userViewModel = profileViewModel,
                                        navController = navController
                                    )
                                }

                                "Tasks" -> {
                                    TasksAppBar(
                                        tasksViewModel = tasksViewModel,
                                        navController = navController
                                    )
                                }

                                else -> {
                                    TopAppBar(
                                        title = { Text(text = " ") },
                                        actions = {

                                        }
                                    )
                                }
                            }
                        },
                        bottomBar = {
                            NavigationBar(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                navigationItems.forEachIndexed { index, item ->
                                    NavigationBarItem(
                                        icon = {
                                            Crossfade(
                                                targetState = selectedIndex == index,
                                                label = ""
                                            ) {
                                                Icon(
                                                    imageVector = if (it) item.selectedIcon else item.unselectedIcon,
                                                    contentDescription = item.title,
                                                )
                                            }
                                        },
                                        label = { Text(text = item.title) },
                                        selected = selectedIndex == index,
                                        onClick = {
                                            if (selectedIndex != index) {
                                                selectedIndex = index
                                                navController.navigate(item.route) {
                                                    popUpTo(navController.graph.startDestinationId) {
                                                        saveState = true
                                                    }
                                                    launchSingleTop = true
                                                    restoreState = true
                                                }
                                            }
                                        },
                                    )
                                }
                            }
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
                                    HomePage(
                                        navController,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(horizontal = 8.dp)
                                    )
                                }
                                composable("tasks") {
                                    TasksPage()
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
