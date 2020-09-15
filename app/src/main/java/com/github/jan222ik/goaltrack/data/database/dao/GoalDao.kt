package com.github.jan222ik.goaltrack.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.github.jan222ik.goaltrack.data.database.entities.GoalEntity

@Dao
abstract class GoalDao {
    @Query("SELECT * FROM GoalEntity")
    abstract suspend fun getAllGoals() : List<GoalEntity>

    @Insert
    abstract fun insertGoal(goalEntity: GoalEntity) : Long

    @Query("SELECT * FROM GoalEntity WHERE id = :id")
    abstract fun getGoalById(id: Long): GoalEntity
}