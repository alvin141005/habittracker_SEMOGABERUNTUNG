package com.example.habittracker_semogaberuntung

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class HabitDB : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: HabitDB? = null

        fun buildDatabase(context: Context): HabitDB {

            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    HabitDB::class.java,
                    "habit_db"
                ).build()
            }

            return INSTANCE!!
        }
    }
}