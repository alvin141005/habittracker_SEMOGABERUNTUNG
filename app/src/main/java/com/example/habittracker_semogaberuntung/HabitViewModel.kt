package com.example.habittracker_semogaberuntung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HabitViewModel : ViewModel() {

    private val _habitList = MutableLiveData<MutableList<Habit>>(mutableListOf())
    val habitList: LiveData<MutableList<Habit>> = _habitList

    fun addHabit(habit: Habit) {
        val currentList = _habitList.value ?: mutableListOf()
        currentList.add(habit)
        _habitList.value = currentList
    }

    fun incrementProgress(position: Int) {
        val currentList = _habitList.value ?: return
        if (position in currentList.indices) {
            val habit = currentList[position]
            if (habit.progress < habit.goal) {
                habit.progress++
                _habitList.value = currentList  // trigger observer
            }
        }
    }

    fun decrementProgress(position: Int) {
        val currentList = _habitList.value ?: return
        if (position in currentList.indices) {
            val habit = currentList[position]
            if (habit.progress > 0) {
                habit.progress--
                _habitList.value = currentList
            }
        }
    }
}