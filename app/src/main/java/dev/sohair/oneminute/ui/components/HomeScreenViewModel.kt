package dev.sohair.oneminute.ui.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.sohair.oneminute.data.TimerState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {
    private val _timerState = MutableStateFlow(TimerState.PAUSED)
    val timerState: StateFlow<TimerState>
        get() = _timerState

    private val _currentTime = MutableStateFlow(60L)
    val currentTime: StateFlow<Long>
        get() = _currentTime
    private val _currentMillis = MutableStateFlow(0L)
    val currentMillis: StateFlow<Long>
        get() = _currentMillis
    private var job: Job? = null

    fun startPauseTimer() {
        if (timerState.value == TimerState.PAUSED) {
            startTimer()
        } else {
            pauseTimer()
        }
    }

    fun stopTimer() {
        job?.cancel()
        _timerState.value = TimerState.PAUSED
        _currentTime.value = 60L
        _currentMillis.value = 0L
    }

    private fun startTimer() {
        job?.cancel()
        job = viewModelScope.launch {
            _timerState.value = TimerState.RUNNING
            for (i in _currentTime.value * 1000 downTo 0 step 10) {
                _currentMillis.value = i
                _currentTime.value = i / 1000
                delay(10L)
            }
            _timerState.value = TimerState.PAUSED
        }
    }

    private fun pauseTimer() {
        job?.cancel()
        _timerState.value = TimerState.PAUSED
    }
}
