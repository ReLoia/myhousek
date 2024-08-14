package it.reloia.myhousek.home.ui

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ChipColors
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    /* TODO: add loading of user data, tasks, etc. */

    Column(
        modifier = modifier
    ) {
//        Row(
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Column {
//                /*todo: add current user name*/
//                Text(text = "Buongiorno, ", fontSize = 28.sp)
//                Text(text = "ci sono N attività da fare", fontSize = 18.sp)
//            }
//            Button(onClick = {
//                navController.navigate("tasks"){
//                    popUpTo(navController.graph.startDestinationId) {
//                        saveState = true
//                    }
//                    launchSingleTop = true
//                    restoreState = true
//                }
//            }) {
//                Text(text = "Vedi")
//            }
//        }
        Card (
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth(),
        ) {
//            Row (
//                modifier = Modifier
//                    .padding(16.dp)
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically,
//            ) {
                Column (
                    modifier = Modifier
                        .padding(
                            horizontal = 16.dp,
                        )
                        .fillMaxWidth(),
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Buongiorno, ", fontSize = 26.sp)
                    Text(text = "ci sono N attività da fare", fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(10.dp))
//                    Row {
                        AssistChip(
                            onClick = {
                                navController.navigate("tasks"){
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            label = { Text(text = "vedi le attività") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Default.List,
                                    contentDescription = "",
                                )
                            },
                            modifier = Modifier
                                .align(Alignment.End),
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                labelColor = MaterialTheme.colorScheme.onPrimary,
                                leadingIconContentColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
//                    }
                }
//                FilledIconButton(
//                    onClick = { /*TODO*/ },
//                ) {
//                    Icon(
//                        imageVector = Icons.AutoMirrored.Default.List,
//                        contentDescription = "",
//                    )
//                }
//            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Column {
            Text(text = "Attività recenti", fontSize = 22.sp)
            /*todo*/
        }
    }
}