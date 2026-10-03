package com.behnamuix.spygame.feature.timer.presentation.contract

data class TimerState(
    var secondsLeft: Int = 0,
    val isRunning: Boolean = false,
    val initialSeconds: Int = 0,
    val formatedTime: String = ""
)

sealed interface UiAction {
    data object StartTimer : UiAction
    data object StopTimer : UiAction
    data object ResumeTimer : UiAction
    data object ResetTimer : UiAction
    data class ShowTimerFormatedString(val currentSec: Int) : UiAction
}