package com.behnamuix.spygame.feature.timer.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.spygame.feature.timer.presentation.contract.UiAction
import com.behnamuix.spygame.feature.timer.presentation.contract.TimerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor() : ViewModel() {
    private val _timerState = MutableStateFlow(TimerState())
    var timerState: StateFlow<TimerState> = _timerState.asStateFlow()
    private var timerJob: Job? = null

    fun onAction(action: UiAction) {
        when (action) {
            is UiAction.StartTimer -> startTimer()
            is UiAction.StopTimer -> stopTimer()
            is UiAction.ResetTimer -> resetTimer()
            is UiAction.ResumeTimer -> resumeTimer()
            is UiAction.ShowTimerFormatedString -> showTimerFormated(action.currentSec)
            else -> {}
        }
    }

    fun setTimer(sec: Int) {
        _timerState.update {
            it.copy(
                secondsLeft = sec
            )
        }
    }

    private fun showTimerFormated(currentSeconds: Int) {
        val min = currentSeconds / 60
        val remainingSec = currentSeconds % 60
        _timerState.update {
            it.copy(
                formatedTime = String.format(
                    Locale.US,
                    "%02d:%02d",
                    min,
                    remainingSec
                )
            )
        }
    }

    private fun resumeTimer() {
        if (!_timerState.value.isRunning && _timerState.value.secondsLeft > 0) {
            startTimer()
        }
    }

    private fun resetTimer() {
        stopTimer()

        _timerState.update {
            it.copy(
                secondsLeft = it.initialSeconds
            )
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
        timerJob = null

        _timerState.update {
            it.copy(isRunning = false)
        }
    }

    private fun startTimer() {
        timerJob?.cancel()

        _timerState.update {
            it.copy(isRunning = true)
        }

        timerJob = viewModelScope.launch {

            while (timerState.value.isRunning &&
                timerState.value.secondsLeft > 0
            ) {

                delay(1000)

                _timerState.update {
                    it.copy(
                        secondsLeft = it.secondsLeft - 1
                    )
                }
                showTimerFormated(_timerState.value.secondsLeft)

            }

            if (timerState.value.secondsLeft == 0) {
                _timerState.update {
                    it.copy(
                        isRunning = false
                    )
                }
            }
        }
    }
}