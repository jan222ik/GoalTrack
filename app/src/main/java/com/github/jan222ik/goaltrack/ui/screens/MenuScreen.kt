package com.github.jan222ik.goaltrack.ui.screens

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import com.github.jan222ik.goaltrack.ui.viewmodel.GoalVM
import com.github.zsoltk.compose.router.BackStack
import java.time.format.DateTimeFormatter


@Composable
fun MenuScreen(backStack: BackStack<Routing>) {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yy hh:mm")
    Scaffold(
        floatingActionButton = {
            Icon(
                modifier = Modifier.clickable {
                    backStack.push(Routing.EditGoal(null))
                },
                asset = Icons.Filled.Add
            )
        }
    ) {
        val viewModel = viewModel(GoalVM::class.java)
        viewModel.init(ContextAmbient.current)
        val allGoals = viewModel.allGoals
        LazyColumnForIndexed(items = allGoals.value) { index, item ->
            Card(
                modifier = Modifier.padding(8.dp)
                    .fillMaxWidth()
                    .clickable(
                        onClick = {
                            backStack.push(Routing.DetailView(item))
                        }, onLongClick = {
                            backStack.push(Routing.EditGoal(item))
                        }
                    ),
                elevation = 8.dp
            ) {
                Column(Modifier.padding(8.dp)) {
                    Row {
                        Text(text = "No: $index")
                        Text(text = item.title)
                    }
                    Row {
                        Text(
                            text = "Duration: ${formatter.format(item.startDateTime)} - ${
                                formatter.format(
                                    item.startDateTime
                                )
                            }"
                        )
                    }
                }
            }
        }
    }
}
