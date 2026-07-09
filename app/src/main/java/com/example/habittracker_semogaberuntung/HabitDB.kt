package com.example.habittracker_semogaberuntung

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [User::class, Habit::class],
    version = 2,
    exportSchema = false
)
abstract class HabitDB : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun habitDao(): HabitDao

    companion object {

        @Volatile
        private var INSTANCE: HabitDB? = null

        fun buildDatabase(context: Context): HabitDB {

            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    HabitDB::class.java,
                    "habit_db"
                )
                    // versi lama cuma punya tabel user, sekarang nambah tabel habit
                    // fallbackToDestructiveMigration cukup buat tugas kuliah (data lama di-reset)
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return INSTANCE!!
        }
    }
}