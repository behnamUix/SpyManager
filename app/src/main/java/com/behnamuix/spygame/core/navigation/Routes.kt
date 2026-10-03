package com.behnamuix.spygame.core.navigation
import kotlinx.serialization.Serializable
@Serializable
data object GameRoute

@Serializable
data class TimerRoute(
    val time: Int
)

@Serializable
data object WordsRoute

@Serializable
data object MapRoute

@Serializable
data object SettingsRoute

@Serializable
data object ConfigRole
