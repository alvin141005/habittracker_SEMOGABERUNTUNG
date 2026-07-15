package com.example.habittracker_semogaberuntung

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val db = HabitDB.buildDatabase(application)

    fun login(
        username: String,
        password: String,
        callback: (Boolean) -> Unit
    ) {

        viewModelScope.launch {

            db.userDao().logoutAll()

            val user = db.userDao().login(username, password)

            if (user != null) {
                db.userDao().setLogin(user.id)
                callback(true)
            } else {
                callback(false)
            }
        }
    }

    fun checkLogin(callback: (Boolean) -> Unit) {

        viewModelScope.launch {

            callback(db.userDao().getCurrentUser() != null)

        }
    }

    fun registerDefaultUser() {

        viewModelScope.launch {

            if (db.userDao().login("admin", "123") == null) {

                db.userDao().insert(
                    User(
                        username = "admin",
                        password = "123"
                    )
                )

            }

        }

    }

}