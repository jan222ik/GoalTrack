package com.github.jan222ik.goaltrack.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class GoalEntity(
    val title: String,
    val description: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val processKey: Long?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}