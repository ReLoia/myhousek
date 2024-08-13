package it.reloia.myhousek.tasks.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksAppBar(
    tasksViewModel: TasksViewModel,
    navController: NavController,
    openAddActivity: MutableState<Boolean>
    ) {

    TopAppBar(
        title = {
            Text("Tasks")
        },
        actions = {
            /* plus */
            IconButton(onClick = {
                openAddActivity.value = true
            }) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add"
                )
            }

        }
    )
}
