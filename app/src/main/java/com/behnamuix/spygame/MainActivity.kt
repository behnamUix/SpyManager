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
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.behnamuix.spygame.core.navigation.AppNavigation
import com.behnamuix.spygame.core.navigation.BottomNavigationBar
import com.behnamuix.spygame.core.theme.SpyTheme

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

                Scaffold(
                    modifier = Modifier.fillMaxSize(),

                    bottomBar = {
                        BottomNavigationBar(
                            navController = navController
                        )
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