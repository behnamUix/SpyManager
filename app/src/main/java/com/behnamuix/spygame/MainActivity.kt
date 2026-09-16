package com.behnamuix.spygame

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.behnamuix.spygame.core.navigation.AppNavigation
import com.behnamuix.spygame.core.navigation.BottomNavigationBar
import com.behnamuix.spygame.core.navigation.ConfigRole
import com.behnamuix.spygame.core.navigation.GameRoute
import com.behnamuix.spygame.core.navigation.MapRoute
import com.behnamuix.spygame.core.navigation.SettingsRoute
import com.behnamuix.spygame.core.navigation.WordsRoute
import com.behnamuix.spygame.core.theme.SpyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                Color.BLACK
            ),
            navigationBarStyle = SystemBarStyle.dark(
                Color.BLACK
            )
        )

        setContent {

            SpyTheme {

                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                Scaffold(
                    modifier = Modifier.fillMaxSize(),

                    bottomBar = {
                        val showBottomBar =
                            currentDestination?.hasRoute<GameRoute>() == true ||
                                    currentDestination?.hasRoute<WordsRoute>() == true ||
                                    currentDestination?.hasRoute<MapRoute>() == true ||
                                    currentDestination?.hasRoute<SettingsRoute>() == true


                        if (showBottomBar) {
                            BottomNavigationBar(navController)
                        }
                    }

                ) { innerPadding ->

                    AppNavigation(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}