package com.behnamuix.spygame.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.behnamuix.spygame.feature.configgame.presentation.screen.ConfigGameSc

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier) {




        NavHost(
            navController = navController,
            startDestination = GameRoute,

        ) {

            composable<GameRoute> {
                ConfigGameSc(navController)
            }

            composable<WordsRoute> {

            }

            composable<MapRoute> {

            }

            composable<SettingsRoute> {

            }
        }

}