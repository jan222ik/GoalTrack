package com.github.jan222ik.goaltrack.ui.screens

import com.github.jan222ik.goaltrack.data.database.entities.GoalEntity

sealed class Routing {
    object Menu : Routing()
    data class DetailView(val goal: GoalEntity): Routing()
    data class EditGoal(val goal: GoalEntity?): Routing()
}