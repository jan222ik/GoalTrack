package com.github.jan222ik.goaltrack.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.jan222ik.goaltrack.data.database.GoalDatabase
import com.github.jan222ik.goaltrack.data.database.entities.GoalEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class GoalVM : ViewModel() {

    val allGoals: MutableState<List<GoalEntity>> = mutableStateOf(listOf())

    fun init(context: Context) {
        viewModelScope.launch(IO) {
            val database = GoalDatabase.getDatabase(context, viewModelScope)
           launch(Main) {
               allGoals.value = database.goalDao().getAllGoals()
           }
        }
    }
}