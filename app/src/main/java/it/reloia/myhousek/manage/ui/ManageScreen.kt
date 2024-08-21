package it.reloia.myhousek.manage.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.reloia.myhousek.R
import it.reloia.myhousek.alarm.domain.model.Alarm
import it.reloia.myhousek.manage.domain.model.Command

@Composable
fun ManageScreen(modifier: Modifier = Modifier) {
//    TODO: add loading of user data, tasks, etc.
//    using view models

//    TODO: remove this
    val alarms = listOf(
        Alarm(
            id = "1",
            name = "Alarm 1",
            apiId = "apiId 1"
        ),
        Alarm(
            id = "2",
            name = "Alarm 1",
            apiId = "apiId 1"
        ),
        Alarm(
            id = "3",
            name = "Alarm 1",
            apiId = "apiId 1"
        ),
        Alarm(
            id = "41",
            name = "Alarm 1",
            apiId = "apiId 1"
        ),
        Alarm(
            id = "51",
            name = "Alarm 1",
            apiId = "apiId 1"
        ),
        Alarm(
            id = "16",
            name = "Alarm 1",
            apiId = "apiId 1"
        ),
        Alarm(
            id = "71",
            name = "Alarm 1",
            apiId = "apiId 1"
        ),
        Alarm(
            id = "81",
            name = "Alarm 1",
            apiId = "apiId 1"
        ),
        Alarm(
            id = "91",
            name = "Alarm 1",
            apiId = "apiId 1"
        ),
    )
    val commands = listOf(
        Command(
            id = "1",
            name = "Command 1",
            apiId = "apiId 1",
            type = "type 1",
            value = "value 1"
        ),
    )

    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.alarms),
            modifier = Modifier.padding(top = 16.dp)
        )
        LazyRow (
            modifier = Modifier,
            userScrollEnabled = true,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = alarms,
                key = { alarm -> alarm.id }
            ) { alarm ->
                Card (
                    modifier = Modifier
                        .width(90.dp)
                        .height(90.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable(onClick = {
                            /* TOOD: */
                        }),

                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = alarm.name, fontSize = 18.sp)
                        Text(text = alarm.apiId, fontSize = 14.sp)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(R.string.commands))
        LazyRow (
            modifier = Modifier,
            userScrollEnabled = true,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = commands,
                key = { command -> command.id }
            ) { command ->
                Card (
                    modifier = Modifier
                        .defaultMinSize(minWidth = 90.dp)
                        .height(90.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable(onClick = {
                            /* TOOD: */
                        }),

                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = command.name, fontSize = 18.sp)
                        Text(text = command.apiId, fontSize = 14.sp)
                    }
                }
            }
        }
    }
}