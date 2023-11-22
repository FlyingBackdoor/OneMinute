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
    val currentTime: StateFlow<Long> get() = _currentTime
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
    }

    private fun startTimer() {
        job?.cancel()
        job = viewModelScope.launch {
            _timerState.value = TimerState.RUNNING
            for (i in _currentTime.value downTo 0) {
                _currentTime.value = i
                delay(1000L)
            }
            _timerState.value = TimerState.PAUSED
        }
    }

    private fun pauseTimer() {
        job?.cancel()
        _timerState.value = TimerState.PAUSED
    }
}
