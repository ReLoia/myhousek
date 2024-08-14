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
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp,
                    )
                    .fillMaxWidth(),
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Buongiorno, ", fontSize = 26.sp)
//                TODO: change this with a loading API message and
//                TODO: then load the data
//                Text(text = "il server si sta avviando...", fontSize = 18.sp)
                Text(text = "ci sono N attività da fare", fontSize = 18.sp)
                Spacer(modifier = Modifier.height(10.dp))
                AssistChip(
                    onClick = {
                        navController.navigate("tasks") {
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
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Column {
            Text(text = "Attività recenti", fontSize = 22.sp)
            /*todo*/
//            TODO: remove:
            Text(text = "Nessuna attività recente", fontSize = 18.sp)
        }
    }
}