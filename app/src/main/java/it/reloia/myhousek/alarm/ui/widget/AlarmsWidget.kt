package it.reloia.myhousek.alarm.ui.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.LocalSize
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.components.CircleIconButton
import androidx.glance.appwidget.components.Scaffold
import androidx.glance.appwidget.components.TitleBar
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import it.reloia.myhousek.MainActivity
import it.reloia.myhousek.R
import it.reloia.myhousek.alarm.data.remote.AlarmsApiService
import it.reloia.myhousek.alarm.data.remote.AlarmsRepositoryImpl
import it.reloia.myhousek.alarm.domain.model.Alarm
import it.reloia.myhousek.alarm.ui.AlarmsViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AlarmsWidget : GlanceAppWidget() {
    companion object {
        val SMALL_SIZE = DpSize(100.dp, 100.dp)
        val MEDIUM_SIZE = DpSize(220.dp, 100.dp)
        val BIG_SIZE = DpSize(340.dp, 100.dp)
    }

    override val sizeMode: SizeMode = SizeMode.Responsive(
        setOf(
            SMALL_SIZE,
            MEDIUM_SIZE,
            BIG_SIZE
        )
    )

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
    val size = LocalSize.current

    val alarmsViewModel: AlarmsViewModel = AlarmsViewModel(
        repository = AlarmsRepositoryImpl(
            alarmsApiService = Retrofit.Builder()
                .baseUrl("https://myhousek-api.onrender.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AlarmsApiService::class.java)
        )
    )

    val alarms: List<Alarm> by alarmsViewModel.alarms.collectAsState(emptyList())
    try {
        alarmsViewModel.loadAlarms()
    } catch (e: Exception) {
        println("Error in resonance")
        e.printStackTrace()
        ErrorScreen()
        return
    }

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
        horizontalPadding = 8.dp,
        backgroundColor = GlanceTheme.colors.widgetBackground
    ) {
        val columnCount = when (size) {
            AlarmsWidget.SMALL_SIZE -> 1
            AlarmsWidget.MEDIUM_SIZE -> 2
            AlarmsWidget.BIG_SIZE -> 3
            else -> 2
        }
        if (alarms.isEmpty()) Text(text = stringResource(R.string.loading))
        LazyColumn (
            modifier = GlanceModifier
                .fillMaxHeight()
        ) {
            val chunked = alarms.chunked(columnCount)
            items( chunked.size ) {
                val row = chunked[it]
                Row (
                    modifier = GlanceModifier
                        .fillMaxWidth()
                        .height(83.dp),
                    horizontalAlignment = Alignment.Horizontal.CenterHorizontally
                ) {
                    row.forEach { alarm ->

                        val boxModifier = when (size) {
                            AlarmsWidget.SMALL_SIZE -> GlanceModifier.fillMaxWidth()
                            AlarmsWidget.MEDIUM_SIZE, AlarmsWidget.BIG_SIZE -> GlanceModifier.defaultWeight()
                            else -> GlanceModifier.width(80.dp)
                        }

                        Box (
                            modifier = boxModifier
                                .height(75.dp)
                                .cornerRadius(8.dp)
                                .background(GlanceTheme.colors.primary)
                                .padding(8.dp)
                        ) {
                            Text(
                                text = alarm.name,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        if (row.indexOf(alarm) != row.size - 1) {
                            Spacer(modifier = GlanceModifier.width(8.dp))
                        }
                    }
                }
            }
        }


    }
}

@Composable
fun ErrorScreen(modifier: GlanceModifier = GlanceModifier) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Error",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun stringResource(id: Int): String {
    val context = LocalContext.current
    return context.getString(id)
}
