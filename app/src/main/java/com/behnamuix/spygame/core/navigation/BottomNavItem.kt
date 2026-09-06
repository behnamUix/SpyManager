package com.behnamuix.spygame.core.navigation

import com.behnamuix.spygame.R

data class BottomNavItem(
    val route: Any,
    val icon: Int,
    val label: String
)
val bottomNavItems = listOf(
    BottomNavItem(
        route = GameRoute,
        icon = R.drawable.icon_game,
        label = "game"
    ),
   BottomNavItem(
        route = WordsRoute,
        icon = R.drawable.icon_database,
        label = "words"
    ),
    BottomNavItem(
        route = MapRoute,
        icon = R.drawable.icon_map,
        label = "map"
    ),
   BottomNavItem(
        route = SettingsRoute,
        icon = R.drawable.icon_setting,
        label = "settings"
    )
)