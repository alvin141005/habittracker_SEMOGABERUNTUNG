package com.example.habittracker_semogaberuntung

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object HabitRepository {

    private const val FILE_NAME = "habits.json"
    private val gson = Gson()

    private var habitList: MutableList<Habit> = mutableListOf()

    fun loadData(context: Context) {
        try {
            val file = context.openFileInput(FILE_NAME)
            val json = file.bufferedReader().use { it.readText() }

            val type = object : TypeToken<MutableList<Habit>>() {}.type
            habitList = gson.fromJson(json, type) ?: mutableListOf()

        } catch (e: Exception) {
            habitList = mutableListOf()
        }
    }

    fun getHabits(): List<Habit> = habitList

    fun addHabit(context: Context, habit: Habit) {
        habitList.add(habit)
        saveData(context)
    }

    fun updateHabit(context: Context, index: Int, habit: Habit) {
        habitList[index] = habit
        saveData(context)
    }

    private fun saveData(context: Context) {
        val json = gson.toJson(habitList)
        context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use {
            it.write(json.toByteArray())
        }
    }
}