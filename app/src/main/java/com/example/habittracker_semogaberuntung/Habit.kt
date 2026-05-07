package com.example.habittracker_semogaberuntung

data class Habit(
    val name: String,
    val description: String,
    val goal: Int,
    var progress: Int = 0,
    val icon: Int,
    val unit: String
)