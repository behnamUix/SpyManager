package com.behnamuix.spygame.feature.timer.presentation.screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    .animateContentSize(tween(1000))
                    .fillMaxSize()
                    .padding(AppDimens.screenPadding)
            ) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(48.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AnimatedVisibility(timerState.isRunning) { Text(
                            "FIND THE SPY!!", color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.headlineMedium
                        )}

                        Box(
                            Modifier.border(
                                BorderStroke(1.dp, Color.White),
                                shape = RoundedCornerShape(24.dp)
                            )
                        ) {
                            Text(
                                modifier = Modifier.padding(24.dp),
                                text = timerState.formatedTime,
                                style = MaterialTheme.typography.displayLarge
                            )
                        }
                        AnimatedVisibility(timerState.isRunning) {
                            LinearProgressIndicator(
                                progress = timerState.prog,
                                Modifier.height(16.dp)
                            )
                        }
                        AnimatedVisibility(
                            visible = !timerState.isRunning,
                            enter = expandVertically(
                                expandFrom = Alignment.Top,
                                animationSpec = tween(
                                    durationMillis = 700
                                )
                            ),
                            exit = shrinkVertically(
                                shrinkTowards = Alignment.Top,
                                animationSpec = tween(
                                    durationMillis = 500
                                )
                            )
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(16.dp)) {
                                Text(
                                    "TIME UP!",
                                    color = MaterialTheme.colorScheme.error,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.headlineMedium
                                )
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp),
                                    shape = RoundedCornerShape(24.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFF17191C)
                                    ),
                                    border = BorderStroke(
                                        1.dp,
                                        Color(0xFF2A2D32)
                                    )
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(20.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {

                                        Text(
                                            text = "OPERATION COMPLETE",
                                            style = MaterialTheme.typography.labelMedium,
                                            color = Color(0xFF9A9A9A),
                                            letterSpacing = 2.sp
                                        )

                                        Spacer(modifier = Modifier.height(8.dp))

                                        Text(
                                            text = "WHO IS THE WINNER?",
                                            style = MaterialTheme.typography.headlineSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFFF5F1E8)
                                        )

                                        Spacer(modifier = Modifier.height(6.dp))

                                        Text(
                                            text = "Select the team that won the mission",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = Color(0xFF85898F)
                                        )

                                        Spacer(modifier = Modifier.height(20.dp))

                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                                        ) {

                                            Button(
                                                onClick = { },
                                                modifier = Modifier.weight(1f),
                                                shape = RoundedCornerShape(16.dp),
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = Color(0xFFBE9A73)
                                                )
                                            ) {
                                                Text(
                                                    text = "AGENT",
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }

                                            OutlinedButton(
                                                onClick = { },
                                                modifier = Modifier.weight(1f),
                                                shape = RoundedCornerShape(16.dp),
                                                border = BorderStroke(
                                                    1.dp,
                                                    Color(0xFFBE9A73)
                                                ),
                                                colors = ButtonDefaults.outlinedButtonColors(
                                                    contentColor = Color(0xFFBE9A73)
                                                )
                                            ) {
                                                Text(
                                                    text = "SPY",
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }
                                    }
                                }

                            }
                        }
                        Row(
                            modifier = Modifier

                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            IconCard(Icons.Default.Home) {

                            }
                            Spacer(Modifier.width(24.dp))
                            IconCard(Icons.Default.Pause) {
                                Log.d("ICON", "CLICKED!")
                                timerViewModel.onAction(UiAction.StopTimer)
                            }
                            Spacer(Modifier.width(24.dp))
                            IconCard(Icons.Default.PlayArrow) {
                                Log.d("ICON", "CLICKED!")
                                timerViewModel.onAction(UiAction.ResumeTimer)
                            }

                        }
                    }

                }


            }
        }



}



@Composable
fun IconCard(icon: ImageVector, iconOnClick: () -> Unit) {

    Card(
        modifier = Modifier

            .size(60.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            IconButton({
                iconOnClick()
            }) {
                Icon(
                    icon,
                    contentDescription = "",
                    modifier = Modifier.size(48.dp)
                )
            }
        }

    }
}