package com.example.habittracker_semogaberuntung

class UserRepos(private val db: HabitDB) {

    suspend fun insert(user: User) {
        db.userDao().insert(user)
    }

    suspend fun login(username: String, password: String): User? {
        return db.userDao().login(username, password)
    }

    suspend fun logoutAll() {
        db.userDao().logoutAll()
    }

    suspend fun setLogin(id: Int) {
        db.userDao().setLogin(id)
    }

    suspend fun getCurrentUser(): User? {
        return db.userDao().getCurrentUser()
    }
}