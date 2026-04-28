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
}