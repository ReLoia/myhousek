package it.reloia.myhousek.widgets.alarms

import android.content.Context
import android.graphics.drawable.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.action.Action
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.components.CircleIconButton
import androidx.glance.appwidget.components.Scaffold
import androidx.glance.appwidget.components.TitleBar
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import it.reloia.myhousek.MainActivity
import it.reloia.myhousek.R
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
//    Column (
//        modifier = GlanceModifier
//            .fillMaxSize()
//            .padding(8.dp)
//            .background(color = MaterialTheme.colorScheme.surface)
//    ) {
//        Text(
//            stringResource(R.string.manage_the_alarms),
//            style = TextStyle(
//                fontSize = 38.sp,
//                fontWeight = FontWeight.Bold,
//
//            )
//        )
////        TODO: automatically load alarms
//        Row {
////            TODO: remove hardcoded
//            Text("Loading")
//        }
//    }
    val coroutineScope = rememberCoroutineScope()

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
        Column (
            modifier = GlanceModifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            //TODO: automatically load alarms
            Row {
                Text("Loading")
            }
        }
    }
}

@Composable
fun stringResource(id: Int): String {
    val context = LocalContext.current
    return context.getString(id)
}
