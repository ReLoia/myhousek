package it.reloia.myhousek.tasks.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TasksPage(modifier: Modifier = Modifier) {
    Column (
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(horizontal = 10.dp)
    ) {
        TaskElement(modifier)
        TaskElement(modifier)
        TaskElement(modifier)
    }
}