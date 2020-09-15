package com.github.jan222ik.goaltrack.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.jan222ik.goaltrack.data.database.converter.DateTimeConverter
import com.github.jan222ik.goaltrack.data.database.entities.GoalEntity

@Database(
    entities = [GoalEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    value = [DateTimeConverter::class]
)
abstract class GoalDatabase : RoomDatabase() {
}