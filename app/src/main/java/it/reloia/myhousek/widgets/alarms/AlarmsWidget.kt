package it.reloia.myhousek.widgets.alarms

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.LocalContext
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import it.reloia.myhousek.R

class AlarmsWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceTheme {
                WidgetScreen()
            }
        }
    }
}

@Composable
fun WidgetScreen() {
    Column (
        modifier = GlanceModifier
            .fillMaxSize()
            .padding(8.dp)
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Text(
            stringResource(R.string.manage_the_alarms),
            style = TextStyle(
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,

            )
        )
//        TODO: automatically load alarms
        Row {
//            TODO: remove hardcoded
            Text("Loading")
        }
    }
}

@Composable
fun stringResource(id: Int): String {
    val context = LocalContext.current
    return context.getString(id)
}
