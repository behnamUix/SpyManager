package com.behnamuix.spygame.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.behnamuix.spygame.feature.configgame.presentation.screen.ConfigGameSc
import com.behnamuix.spygame.feature.configword.presentation.screen.WordManagerSc

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier) {




        NavHost(
            navController = navController,
            startDestination = WordsRoute,

        ) {

            composable<GameRoute> {
                ConfigGameSc(navController)
            }

            composable<WordsRoute> {
                WordManagerSc()

            }

            composable<MapRoute> {

            }

            composable<SettingsRoute> {

            }
        }

}