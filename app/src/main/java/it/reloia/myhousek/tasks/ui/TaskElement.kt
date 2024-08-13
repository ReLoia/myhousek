package it.reloia.myhousek.tasks.ui

import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.reloia.myhousek.tasks.domain.model.Task

@Composable
fun TaskElement(
    task: Task,
    modifier: Modifier = Modifier
) {
    var isChecked by remember { mutableStateOf(task.isCompleted) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color(0xffD9D9D9), shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp)
            .height(38.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom,

        ) {
        // TODO: change the colour
        Text(text = task.title)
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(color = Color.Blue, shape = RoundedCornerShape(6.dp))
                .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(6.dp))
                .clickable(onClick = {
                    isChecked = !isChecked
                }),
            contentAlignment = Alignment.Center
        ) {
            println(isChecked)
            if (isChecked) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Task completed",
                    modifier = Modifier.size(16.dp),
                    tint = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun TaskElementPreview() {
    TaskElement(
        Task(
            id = "1",
            title = "Task 1",
            description = "Description for Task 1",
            isCompleted = false,
            assignedUsers = emptyList(),
            timestamp = System.currentTimeMillis(),
            author = "Author"
        )
    )
}
