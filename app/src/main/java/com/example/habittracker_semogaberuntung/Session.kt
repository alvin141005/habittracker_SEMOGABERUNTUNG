package com.example.habittracker_semogaberuntung

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("HabitAppSession", Context.MODE_PRIVATE)

    fun saveLoginSession(username: String) {
        prefs.edit().apply {
            putBoolean("IS_LOGGED_IN", true)
            putString("USERNAME", username)
            apply()
        }
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean("IS_LOGGED_IN", false)
    }
}