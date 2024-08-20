package it.reloia.myhousek.widgets.alarms

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.components.CircleIconButton
import androidx.glance.appwidget.components.Scaffold
import androidx.glance.appwidget.components.TitleBar
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import it.reloia.myhousek.MainActivity
import it.reloia.myhousek.R
import it.reloia.myhousek.tasks.domain.model.Task
import kotlinx.coroutines.launch

class AlarmsWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceTheme {
                WidgetScreen(
                    context = context,
                    id = id
                )
            }
        }
    }
}

@Composable
fun WidgetScreen(context: Context, id: GlanceId) {
    val coroutineScope = rememberCoroutineScope()

//    TODO: remove boilerplate
    var alarms: List<Task> = emptyList()

    Scaffold (
        titleBar = {
            TitleBar(
                startIcon = ImageProvider(R.mipmap.ic_launcher),
                title = stringResource(R.string.alarms),
                actions = {
                    CircleIconButton(
                        imageProvider = ImageProvider(R.drawable.baseline_refresh_24),
                        contentDescription = stringResource(R.string.refresh),
                        onClick = {
                            coroutineScope.launch {
                                AlarmsWidget().update(context, id)
                            }
                        },
                        backgroundColor = GlanceTheme.colors.widgetBackground
                    )
                },
                modifier = GlanceModifier
                    .clickable(actionStartActivity<MainActivity>())
            )
        },
        backgroundColor = GlanceTheme.colors.widgetBackground
    ) {
        val columnCount = 2
        val rowCount: Int = alarms.size / columnCount
        LazyColumn (
            modifier = GlanceModifier
                .fillMaxSize()
                .background(Color(0xFF000000))
        ) {
            println("$rowCount $columnCount")
//            (0..<rowCount.toInt()).map { c ->
//                Row (
//                    modifier = GlanceModifier
//                        .height(100.dp)
//                        .padding(8.dp),
//                ) {
//                    (0..<columnCount.toInt()).map { r ->
//                        println("col: ${r + c * columnCount.toInt()}")
//                        Box (
//                            modifier = GlanceModifier
//                                .height(100.dp)
//                                .width(100.dp)
//                                .padding(8.dp)
//                                .background(GlanceTheme.colors.surface)
//                        ) {
//                            Text(
//                                text = "Alarm ${r + c * columnCount.toInt()}",
//                                style = TextStyle(
//                                    fontSize = 16.sp,
//                                    fontWeight = FontWeight.Bold
//                                )
//                            )
//                        }
//                    }
//                }
//            }
            items(rowCount.toInt()) {
                Row (
                    modifier = GlanceModifier
                        .height(100.dp)
                        .padding(8.dp),
                ) {
                    (0 until columnCount.toInt()).map { r ->
                        println("col: ${r + it * columnCount.toInt()}")
                        Box (
                            modifier = GlanceModifier
                                .height(100.dp)
                                .width(100.dp)
                                .padding(8.dp)
                                .background(GlanceTheme.colors.surface)
                        ) {
                            Text(
                                text = "Alarm ${r + it * columnCount.toInt()}",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                }
            }
        }


    }
}

@Composable
fun stringResource(id: Int): String {
    val context = LocalContext.current
    return context.getString(id)
}
