package com.behnamuix.spygame.feature.timer.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.behnamuix.appointment.const.BACKGROUND_URL
import com.behnamuix.spygame.core.theme.AppDimens
import com.behnamuix.spygame.feature.timer.presentation.contract.UiAction
import com.behnamuix.spygame.feature.timer.presentation.viewmodel.TimerViewModel

@Composable
fun TimerSc(timerViewModel: TimerViewModel = hiltViewModel(), sec: Int) {
    val timerState by timerViewModel.timerState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        timerViewModel.setTimer(sec)
        timerViewModel.onAction(UiAction.StartTimer)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFBE9A73))
    ) {


        AsyncImage(
            model = BACKGROUND_URL,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(AppDimens.screenPadding)
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(48.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "FIND THE SPY!!", color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        timerState.formatedTime,
                        style = MaterialTheme.typography.displayLarge
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(32.dp)
                    ) {
                        Card(
                            modifier = Modifier

                                .size(60.dp),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                IconButton({}) {
                                    Icon(
                                        Icons.Default.Home,
                                        contentDescription = "",
                                        modifier = Modifier.size(48.dp)
                                    )
                                }
                            }

                        }
                        Card(
                            modifier = Modifier

                                .size(60.dp),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                IconButton({}) {
                                    Icon(
                                        Icons.Default.Pause,
                                        contentDescription = "",
                                        modifier = Modifier.size(48.dp)
                                    )
                                }
                            }
                        }
                    }
                }

            }


        }
    }

}