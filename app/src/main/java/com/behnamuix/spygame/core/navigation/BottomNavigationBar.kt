package com.behnamuix.spygame.core.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()

    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(76.dp)
                .clip(
                    RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 0.dp, bottomEnd = 0.dp)
                )
                .background(
                    Color(0xFF17191F)
                )
               ,
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            bottomNavItems.forEach { item ->

                val selected = currentDestination
                    ?.hasRoute(item.route::class) == true

                BottomNavigationItem(
                    item = item,
                    selected = selected,
                    onClick = {

                        navController.navigate(item.route) {

                            popUpTo(
                                navController.graph.startDestinationId
                            ) {
                                saveState = true
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}
@Composable
private fun BottomNavigationItem(
    item: BottomNavItem,
    selected: Boolean,
    onClick: () -> Unit
) {

    val backgroundColor by animateColorAsState(
        targetValue = if (selected) {
            Color(0xff1D2228).copy(alpha = 0.35f)
        } else {
            Color.Transparent
        },
        label = "navigation_background"
    )

    val horizontalPadding by animateDpAsState(
        targetValue = if (selected) {
            18.dp
        } else {
            12.dp
        },
        label = "navigation_padding"
    )

    Row(
        modifier = Modifier
            .clip(
                RoundedCornerShape(12.dp)
            )
            .background(backgroundColor)
            .clickable(
                onClick = onClick
            )
            .animateContentSize()
            .padding(
                horizontal = horizontalPadding,
                vertical = 6.dp
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(item.icon),
            contentDescription = item.label,
            modifier = Modifier.size(34.dp),
            tint = Color.Unspecified
        )

        if (selected) {

            Spacer(
                modifier = Modifier.size(8.dp)
            )

            Text(
                text = item.label,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}