package com.github.jan222ik.goaltrack.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.github.jan222ik.goaltrack.data.database.converter.DateTimeConverter
import com.github.jan222ik.goaltrack.data.database.dao.GoalDao
import com.github.jan222ik.goaltrack.data.database.entities.GoalEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@Database(
    entities = [GoalEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    value = [DateTimeConverter::class]
)
abstract class GoalDatabase : RoomDatabase() {
    abstract fun goalDao(): GoalDao

    companion object {
        @Volatile
        private var INSTANCE: GoalDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): GoalDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE
                ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        GoalDatabase::class.java,
                        "goal_database"
                    )
                        .addCallback(
                            DiveDatabaseCallback(
                                scope
                            )
                        )
                        .build()
                    INSTANCE = instance
                    // return instance
                    instance
                }
        }

        private class DiveDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        database.goalDao().insertGoal(
                            GoalEntity(
                                title = "Test",
                                description = "Test description",
                                startDateTime = LocalDateTime.now(),
                                endDateTime = LocalDateTime.now().plusDays(7),
                                processKey = null
                            )
                        )
                    }
                }
            }
        }
    }
}