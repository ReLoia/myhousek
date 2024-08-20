package it.reloia.myhousek.tasks.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ListItem
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import it.reloia.myhousek.tasks.domain.model.Task
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TasksScreen(viewModel: TasksViewModel, modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()

    val tabs = listOf("All", "Pending", "Completed")

    val pagerState = rememberPagerState(pageCount = { tabs.size })

//    TODO: remove
    val tasks by viewModel.tasks.collectAsState()
    print(tasks)

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(horizontal = 10.dp)
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,

            ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }

                    },
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
        ) { page ->
//            TODO: use LazyColumn
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                when (page) {
                    0 -> {
                        items(
                            items = tasks,
                            key = { task -> task.id }
                        ) { task ->
                            ListItem(
                                headlineContent = { Text(text = task.title) },
                                supportingContent = {
                                    Text(text = task.description.let {
                                        if (it.length > 50) it.substring(0, 40) + "..." else it
                                    })
                                },
                                trailingContent = {
                                    Checkbox(
                                        checked = task.isCompleted,
                                        onCheckedChange = { isChecked ->
                                            task.isCompleted = isChecked
                                        }
                                    )
                                },
                                overlineContent = { Text(text = task.author) }
                            )
                        }
                    }

                    1 -> {
                        items(
                            items = tasks.filter { !it.isCompleted },
                            key = { task -> task.id }
                        ) { task ->
                            ListItem(
                                headlineContent = { Text(text = task.title) },
                                supportingContent = {
                                    Text(text = task.description.let {
                                        if (it.length > 50) it.substring(0, 40) + "..." else it
                                    })
                                },
                                trailingContent = {
                                    Checkbox(
                                        checked = task.isCompleted,
                                        onCheckedChange = { isChecked ->
                                            task.isCompleted = isChecked
                                        }
                                    )
                                },
                                overlineContent = { Text(text = task.author) }
                            )
                        }
                    }

                    2 -> {
                        items(
                            items = tasks.filter { it.isCompleted },
                            key = { task -> task.id }
                        ) { task ->
                            ListItem(
                                headlineContent = { Text(text = task.title) },
                                supportingContent = {
                                    Text(text = task.description.let {
                                        if (it.length > 50) it.substring(0, 40) + "..." else it
                                    })
                                },
                                trailingContent = {
                                    Checkbox(
                                        checked = task.isCompleted,
                                        onCheckedChange = { isChecked ->
                                            task.isCompleted = isChecked
                                        }
                                    )
                                },
                                overlineContent = { Text(text = task.author) }
                            )
                        }
                    }
                }
            }
        }
    }
}