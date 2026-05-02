package com.example.habittracker_semogaberuntung

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HabitViewModel : ViewModel() {

    // ambil data
    private val _habitList = MutableLiveData<List<Habit>>()
    val habitList: LiveData<List<Habit>> = _habitList

    fun loadHabits(context: Context) {
        HabitRepository.loadData(context)
        _habitList.value = HabitRepository.getHabits()
    }

    fun addHabit(context: Context, habit: Habit) {
        HabitRepository.addHabit(context, habit)
        _habitList.value = HabitRepository.getHabits()
    }

    fun incrementProgress(context: Context, position: Int) {
        val currentList = HabitRepository.getHabits().toMutableList()

        if (position in currentList.indices) {
            val habit = currentList[position]
            if (habit.progress < habit.goal) {
                habit.progress++
                HabitRepository.updateHabit(context, position, habit)
                _habitList.value = HabitRepository.getHabits()
            }
        }
    }

    fun decrementProgress(context: Context, position: Int) {
        val currentList = HabitRepository.getHabits().toMutableList()

        if (position in currentList.indices) {
            val habit = currentList[position]
            if (habit.progress > 0) {
                habit.progress--
                HabitRepository.updateHabit(context, position, habit)
                _habitList.value = HabitRepository.getHabits()
            }
        }
    }
}