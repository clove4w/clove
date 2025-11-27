package com.clove.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.clove.data.MeditationAlarm

class AppPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "clove_app_prefs",
        Context.MODE_PRIVATE
    )

    companion object {
        private const val IS_FIRST_LAUNCH = "is_first_launch"
        private const val USER_NAME = "user_name"
        private const val LAST_STRESS_RELIEF_TIME = "last_stress_relief_time"
        private const val PROFILE_PHOTO_URI = "profile_photo_uri"
        private const val ALARMS_LIST = "alarms_list"
        private const val MOOD_ENTRIES = "mood_entries"
        private const val STRESS_ENTRIES = "stress_entries"
    }

    private val gson = Gson()

    // Check if this is the first launch of the app
    fun isFirstLaunch(): Boolean {
        return sharedPreferences.getBoolean(IS_FIRST_LAUNCH, true)
    }

    // Mark the app as launched (completing onboarding)
    fun markOnboardingComplete() {
        sharedPreferences.edit().apply {
            putBoolean(IS_FIRST_LAUNCH, false)
            apply()
        }
    }

    // Save user name
    fun saveUserName(name: String) {
        sharedPreferences.edit().apply {
            putString(USER_NAME, name)
            apply()
        }
    }

    // Get saved user name
    fun getUserName(): String {
        return sharedPreferences.getString(USER_NAME, "User") ?: "User"
    }

    // Get last stress relief time in milliseconds
    fun getLastStressReliefTime(): Long {
        return sharedPreferences.getLong(LAST_STRESS_RELIEF_TIME, 0)
    }

    // Update last stress relief time to current time
    fun updateLastStressReliefTime() {
        sharedPreferences.edit().apply {
            putLong(LAST_STRESS_RELIEF_TIME, System.currentTimeMillis())
            apply()
        }
    }

    // Check if enough time has passed since last stress relief (6-7 hours)
    fun shouldShowStressRelief(): Boolean {
        val lastTime = getLastStressReliefTime()
        if (lastTime == 0L) return false // First time, no stress relief yet

        val currentTime = System.currentTimeMillis()
        val elapsedTime = currentTime - lastTime

        // 6-7 hours in milliseconds (randomized between 6 and 7 hours)
        val sixHours = 6 * 60 * 60 * 1000
        val sevenHours = 7 * 60 * 60 * 1000

        return elapsedTime >= sixHours && elapsedTime <= sevenHours
    }

    // Force show stress relief (for testing or manual trigger)
    fun forceShowStressRelief() {
        updateLastStressReliefTime()
    }

    // Save profile photo URI
    fun saveProfilePhotoUri(uri: String) {
        sharedPreferences.edit().apply {
            putString(PROFILE_PHOTO_URI, uri)
            apply()
        }
    }

    // Get saved profile photo URI
    fun getProfilePhotoUri(): String? {
        return sharedPreferences.getString(PROFILE_PHOTO_URI, null)
    }

    // Save alarms list
    fun saveAlarms(alarms: List<MeditationAlarm>) {
        sharedPreferences.edit().apply {
            putString(ALARMS_LIST, gson.toJson(alarms))
            apply()
        }
    }

    // Get alarms list
    fun getAlarms(): List<MeditationAlarm> {
        val json = sharedPreferences.getString(ALARMS_LIST, "[]") ?: "[]"
        return try {
            val type = object : TypeToken<List<MeditationAlarm>>() {}.type
            gson.fromJson(json, type) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    // Get next upcoming alarm
    fun getNextUpcomingAlarm(): MeditationAlarm? {
        val alarms = getAlarms()
        if (alarms.isEmpty()) return null

        val currentHour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
        val currentMinute = java.util.Calendar.getInstance().get(java.util.Calendar.MINUTE)

        // Find alarms that haven't passed today
        val upcomingAlarms = alarms.filter { alarm ->
            alarm.hour > currentHour || (alarm.hour == currentHour && alarm.minute > currentMinute)
        }

        // Return the earliest upcoming alarm
        return upcomingAlarms.minByOrNull { it.hour * 60 + it.minute }
            ?: alarms.minByOrNull { it.hour * 60 + it.minute } // If no upcoming alarm today, get earliest alarm for tomorrow
    }

    // Save mood entry
    fun saveMoodEntry(mood: String) {
        val moodEntries = getMoodEntries().toMutableList()
        val timestamp = System.currentTimeMillis()
        moodEntries.add(mapOf("mood" to mood, "timestamp" to timestamp))

        // Keep only last 30 entries
        if (moodEntries.size > 30) {
            moodEntries.removeAt(0)
        }

        sharedPreferences.edit().apply {
            putString(MOOD_ENTRIES, gson.toJson(moodEntries))
            apply()
        }
    }

    // Get all mood entries
    fun getMoodEntries(): List<Map<String, Any>> {
        val json = sharedPreferences.getString(MOOD_ENTRIES, "[]") ?: "[]"
        return try {
            val type = object : TypeToken<List<Map<String, Any>>>() {}.type
            gson.fromJson(json, type) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    // Set mood entries (for updating the list directly)
    fun setMoodEntries(entries: List<Map<String, Any>>) {
        sharedPreferences.edit().apply {
            putString(MOOD_ENTRIES, gson.toJson(entries))
            apply()
        }
    }

    // Save stress entry
    fun saveStressEntry(stress: String) {
        val stressEntries = getStressEntries().toMutableList()
        val timestamp = System.currentTimeMillis()
        stressEntries.add(mapOf("stress" to stress, "timestamp" to timestamp))

        // Keep only last 30 entries
        if (stressEntries.size > 30) {
            stressEntries.removeAt(0)
        }

        sharedPreferences.edit().apply {
            putString(STRESS_ENTRIES, gson.toJson(stressEntries))
            apply()
        }
    }

    // Get all stress entries
    fun getStressEntries(): List<Map<String, Any>> {
        val json = sharedPreferences.getString(STRESS_ENTRIES, "[]") ?: "[]"
        return try {
            val type = object : TypeToken<List<Map<String, Any>>>() {}.type
            gson.fromJson(json, type) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    // Reset all preferences (for testing)
    fun resetPreferences() {
        sharedPreferences.edit().clear().apply()
    }
}
