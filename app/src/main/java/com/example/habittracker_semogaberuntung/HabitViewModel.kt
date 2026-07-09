package com.example.habittracker_semogaberuntung

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HabitViewModel(application: Application) : AndroidViewModel(application) {

    private val db = HabitDB.buildDatabase(application)

    private val _habitList = MutableLiveData<List<Habit>>()
    val habitList: LiveData<List<Habit>> = _habitList

    // ambil semua habit dari tabel habit (room)
    fun loadHabits() {
        viewModelScope.launch {
            _habitList.value = db.habitDao().getAll()
        }
    }

    // dipakai halaman New Habit
    fun addHabit(habit: Habit) {
        viewModelScope.launch {
            db.habitDao().insert(habit)
            loadHabits()
        }
    }

    // dipakai halaman Edit Habit (submit -> update ke tabel habit)
    fun updateHabit(habit: Habit) {
        viewModelScope.launch {
            db.habitDao().update(habit)
            loadHabits()
        }
    }

    // dipakai halaman Edit Habit buat ambil data awal berdasarkan id
    fun getHabitById(id: Int, callback: (Habit?) -> Unit) {
        viewModelScope.launch {
            callback(db.habitDao().getById(id))
        }
    }

    fun incrementProgress(habit: Habit) {
        viewModelScope.launch {
            if (habit.progress < habit.goal) {
                habit.progress++
                db.habitDao().update(habit)
                loadHabits()
            }
        }
    }

    fun decrementProgress(habit: Habit) {
        viewModelScope.launch {
            if (habit.progress > 0) {
                habit.progress--
                db.habitDao().update(habit)
                loadHabits()
            }
        }
    }
}