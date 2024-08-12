package it.reloia.myhousek.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomePage(navController: NavController, modifier: Modifier = Modifier) {
    /* TODO: add loading of user data, tasks, etc. */

    Column(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                /*todo: add current user name*/
                Text(text = "Buongiorno, ", fontSize = 28.sp)
                Text(text = "ci sono N attività da fare.", fontSize = 18.sp)
            }
            Button(onClick = {
                navController.navigate("tasks")
            }) {
                Text(text = "Vedi")
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Column {
            Text(text = "Attività recenti", fontSize = 22.sp)
            /*todo*/
        }
    }
}