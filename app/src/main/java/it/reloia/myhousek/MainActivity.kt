package it.reloia.myhousek

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.sharp.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.reloia.myhousek.home.ui.HomeScreen
import it.reloia.myhousek.manage.ui.ManageScreen
import it.reloia.myhousek.home.ui.HomeAppBar
import it.reloia.myhousek.profile.data.remote.ProfileApiService
import it.reloia.myhousek.profile.data.remote.RemoteProfileRepositoryImpl
import it.reloia.myhousek.profile.ui.ProfileViewModel
import it.reloia.myhousek.tasks.data.remote.TasksApiService
import it.reloia.myhousek.tasks.data.remote.TasksRepositoryImpl
import it.reloia.myhousek.tasks.ui.TasksAppBar
import it.reloia.myhousek.tasks.ui.TasksScreen
import it.reloia.myhousek.tasks.ui.TasksViewModel
import it.reloia.myhousek.ui.theme.MyhouseKTheme
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

data class NavBarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String = title.lowercase(),
)

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loggedIn: Boolean = getSharedPreferences("it.reloia.myhousek.PREFERENCE_FILE_KEY", Context.MODE_PRIVATE)
            .getBoolean("loggedIn", false)

        if (!loggedIn) {
            val intent = Intent(this, OtherActivity::class.java)
            intent.putExtra("page", "profile")

            startActivity(intent)
            return@onCreate
        }

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            /* TODO: replace with actual image */
            val profileImage = null;
            val profileViewModel = ProfileViewModel(
                RemoteProfileRepositoryImpl(
                    Retrofit.Builder()
                        .baseUrl("https://myhousek-api.onrender.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(ProfileApiService::class.java),
                    this.application
                )
            )
            val loginToken: String = getSharedPreferences("myhousek", Context.MODE_PRIVATE)
                .getString("token", "").toString()

            if (loginToken.isBlank()) {
                println("No token found")
                val intent = Intent(this, OtherActivity::class.java)
                intent.putExtra("page", "login")

                startActivity(intent)
                return@setContent
            }

            val httpClient = OkHttpClient.Builder()
                .addInterceptor {
                    val request = it.request().newBuilder()
                        .addHeader("Authorization", "Bearer $loginToken")
                        .build()
                    it.proceed(request)
                }

            val tasksViewModel = TasksViewModel(
                TasksRepositoryImpl(
                    Retrofit.Builder()
                        .baseUrl("https://myhousek-api.onrender.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(TasksApiService::class.java),
                    this.application
                )
            )
            val openAddTask = remember { mutableStateOf(false) }
            val modalBottomSheetState = rememberModalBottomSheetState()

            var newTaskTitle by remember { mutableStateOf("") }
            var newTaskDescription by remember { mutableStateOf("") }
            var newTaskAssignedUsers by remember { mutableStateOf("") }


            val navigationItems = listOf(
                NavBarItem(
                    stringResource(R.string.tasks),
                    Icons.AutoMirrored.Filled.List,
                    Icons.AutoMirrored.Sharp.List,
                    "tasks"
                ),
                NavBarItem(
                    stringResource(R.string.home),
                    Icons.Default.Home,
                    Icons.Outlined.Home,
                    "home"
                ),
                NavBarItem(
                    stringResource(R.string.manage),
                    ImageVector.vectorResource(id = R.drawable.baseline_tune_24),
                    ImageVector.vectorResource(id = R.drawable.baseline_tune_24),
                    "manage"
                ),
            )
            var selectedIndex by rememberSaveable {
                mutableIntStateOf(1)
            }

            val coroutineScope = rememberCoroutineScope()

            LaunchedEffect(navController) {
                navController.currentBackStackEntryFlow.collect { backStackEntry ->
                    val route = backStackEntry.destination.route
                    selectedIndex = navigationItems.indexOfFirst { it.route == route }.let {
                        if (it != -1) it else 1
                    }
                }
            }

            MyhouseKTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    /*color = MaterialTheme.colorScheme.background,*/
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            when (navigationItems[selectedIndex].route) {
                                "home" -> {
                                    HomeAppBar(
                                        userViewModel = profileViewModel,
                                        navController = navController
                                    )
                                }

                                "tasks" -> {
                                    TasksAppBar(
                                        tasksViewModel = tasksViewModel,
                                        navController = navController,
                                        modalBottomSheetState = modalBottomSheetState,
                                        openAddTask = openAddTask
                                    )
                                }

                                else -> {
                                    TopAppBar(
                                        title = { Text(text = navigationItems[selectedIndex].title) },
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
                        if (navigationItems[selectedIndex].route == "tasks" && openAddTask.value) {
                            ModalBottomSheet(onDismissRequest = {
                                openAddTask.value = false
                            }, sheetState = modalBottomSheetState) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(text = "Create a new task", fontSize = 20.sp)

                                        AssistChip(onClick = {
                                            if (newTaskTitle != "" && newTaskDescription != "")
                                                tasksViewModel.addTask(
                                                    newTaskTitle,
                                                    newTaskDescription,
                                                    newTaskAssignedUsers.split(",")
                                                )
                                            else {
                                                // TODO:
                                            }
                                            coroutineScope.launch {
                                                modalBottomSheetState.hide()
                                                openAddTask.value = false
                                            }
                                        }, label = {
                                            Text("Add")
                                        }, leadingIcon = {
                                            Icon(
                                                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_add_task_24),
                                                contentDescription = "Add",
                                                modifier = Modifier.size(20.dp)
                                            )
                                        })

                                    }
//                                    Spacer(modifier = Modifier.height(8.dp))
//                                    TODO: if the user is not logged in, the task is not created
                                    Text(text = "Title")
                                    TextField(
                                        value = newTaskTitle,
                                        onValueChange = { newTaskTitle = it },
//                                        label = { Text("Title") },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(text = "Description")
                                    TextField(
                                        value = newTaskDescription,
                                        onValueChange = { newTaskDescription = it },
//                                        label = { Text("Description") },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(text = "Assigned users")
                                    TextField(
                                        value = newTaskAssignedUsers,
                                        onValueChange = { newTaskAssignedUsers = it },
//                                        label = { Text("Assigned users") },
                                        modifier = Modifier.fillMaxWidth(),
                                        placeholder = { Text("renato, rovazzi, ...") }
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                }
                            }
                        }

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
                                    HomeScreen(
                                        navController,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(horizontal = 8.dp)
                                    )
                                }
                                composable("tasks") {
                                    TasksScreen( tasksViewModel )
                                }
                                composable("manage") {
                                    ManageScreen()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
