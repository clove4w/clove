package com.clove.utils

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import kotlinx.coroutines.CoroutineScope

class MeditationTimer {
    private var timerJob: Job? = null
    private var _remainingTime = mutableStateOf(0)
    val remainingTime: State<Int> = _remainingTime

    private var _isRunning = mutableStateOf(false)
    val isRunning: State<Boolean> = _isRunning

    fun startTimer(durationMinutes: Int, scope: CoroutineScope) {
        if (_isRunning.value) return

        _remainingTime.value = durationMinutes * 60
        _isRunning.value = true

        timerJob = scope.launch {
            while (_remainingTime.value > 0 && _isRunning.value) {
                delay(1000L)
                _remainingTime.value -= 1
            }
            if (_remainingTime.value == 0) {
                _isRunning.value = false
            }
        }
    }

    fun pauseTimer() {
        _isRunning.value = false
        timerJob?.cancel()
    }

    fun resumeTimer(scope: CoroutineScope) {
        if (_isRunning.value) return

        _isRunning.value = true
        timerJob = scope.launch {
            while (_remainingTime.value > 0 && _isRunning.value) {
                delay(1000L)
                _remainingTime.value -= 1
            }
            if (_remainingTime.value == 0) {
                _isRunning.value = false
            }
        }
    }

    fun stopTimer() {
        _isRunning.value = false
        timerJob?.cancel()
        _remainingTime.value = 0
    }

    fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val secs = seconds % 60
        return String.format("%02d:%02d", minutes, secs)
    }
}
