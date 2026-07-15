package com.example.habittracker_semogaberuntung

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HabitDao {

    @Query("SELECT * FROM habit")
    suspend fun getAll(): List<Habit>

    @Query("SELECT * FROM habit WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): Habit?

    @Insert
    suspend fun insert(habit: Habit)

    @Update
    suspend fun update(habit: Habit)
}