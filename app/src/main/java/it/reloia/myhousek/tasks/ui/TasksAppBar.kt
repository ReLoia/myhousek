package it.reloia.myhousek.tasks.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import it.reloia.myhousek.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksAppBar(
    tasksViewModel: TasksViewModel,
    navController: NavController) {

    TopAppBar(
        title = {
            Text("Tasks")
        },
        actions = {
            /* plus */
            IconButton(onClick = {
                /*todo*/
                /*navController.navigate("settings")*/
            }) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add"
                )
            }

        }
    )
}
