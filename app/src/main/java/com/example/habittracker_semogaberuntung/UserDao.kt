package com.example.habittracker_semogaberuntung

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM user WHERE username = :username AND password = :password LIMIT 1")
    suspend fun login(username: String, password: String): User?

    @Query("SELECT * FROM user WHERE is_login = 1 LIMIT 1")
    suspend fun getCurrentUser(): User?

    @Query("UPDATE user SET is_login = 0")
    suspend fun logoutAll()

    @Query("UPDATE user SET is_login = 1 WHERE id = :id")
    suspend fun setLogin(id: Int)
}