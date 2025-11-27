package com.clove.data

data class MeditationAlarm(
    val id: String,
    val hour: Int,
    val minute: Int,
    val label: String = "Meditate",
    val isEnabled: Boolean = true,
    val daysOfWeek: List<Int> = emptyList(), // 0=Sunday, 1=Monday, etc. Empty = once
    val createdAt: Long = System.currentTimeMillis()
)
