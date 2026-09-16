package com.behnamuix.spygame.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.behnamuix.spygame.feature.configgame.presentation.screen.ConfigGameSc
import com.behnamuix.spygame.feature.configrole.presentation.screen.ConfigRoleSc
import com.behnamuix.spygame.feature.configword.presentation.screen.WordManagerSc

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier) {


    NavHost(
        navController = navController,
        startDestination = WordsRoute,

        ) {

        composable<GameRoute>(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ) {
            ConfigGameSc() {
                navController.navigate(ConfigRole)
            }
        }

        composable<WordsRoute> {
            WordManagerSc()

        }

        composable<MapRoute> {

        }

        composable<SettingsRoute> {

        }
        composable<ConfigRole>(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,

                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,

                )
            }

        ) {
            ConfigRoleSc(backToHome = {
                navController.navigate(GameRoute)
            })
        }
    }

}