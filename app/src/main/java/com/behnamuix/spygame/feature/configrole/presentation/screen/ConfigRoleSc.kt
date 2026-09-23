package com.behnamuix.spygame.feature.configrole.presentation.screen

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.KeyboardDoubleArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.behnamuix.spygame.core.theme.Traffic
import com.behnamuix.spygame.feature.configrole.domain.model.Player
import com.behnamuix.spygame.feature.configrole.presentation.contract.ConfigRoleContract
import com.behnamuix.spygame.feature.configrole.presentation.viewmodel.ConfigRoleViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigRoleSc(
    roleVm: ConfigRoleViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    backToHome: () -> Unit
) {
    val state = roleVm.configRoleState.collectAsStateWithLifecycle()
    val currentPlayer =
        state.value.playerList.getOrNull(state.value.currentPlayerIndex)
    state.value.userUse = false

    LaunchedEffect(Unit) {
        roleVm.onAction(ConfigRoleContract.ConfigRoleAction.configRole)

    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(Color(0xFFBE9A73)),

                title = { Text("") },
                navigationIcon = {
                    IconButton({
                        backToHome()
                    }) {
                        Icon(
                            Icons.Default.ArrowBackIos,
                            contentDescription = "",
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }

                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Color(0xFFBE9A73))
        ) {
            Column(
                Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                CensoredText()
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Spacer(Modifier.height(32.dp))
                    Box() {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(


                                    color = MaterialTheme.colorScheme.secondary,
                                    text = "TAP ON HERE",
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Bold,

                                    )
                                Icon(
                                    tint = MaterialTheme.colorScheme.background,
                                    modifier = Modifier.size(32.dp),
                                    imageVector = Icons.Default.KeyboardDoubleArrowDown,
                                    contentDescription = ""
                                )
                            }
                        }
                        Log.d("ROLE", "${state.value.playerList.size}")
                        if (state.value.finished) {
                            Log.d("ROLE", "DONE!")
                        } else {
                            SecretEnvelope(

                                player = currentPlayer,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp),
                                title = "SECRET ROLE",

                                ) {
                                roleVm.onAction(ConfigRoleContract.ConfigRoleAction.nextPlayer)

                            }
                        }


                    }


                }
                Text(
                    text = "DO NOT \n PHOTOCOPY", style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, color = Color(
                        0xFF3F51B5
                    )
                )


            }
        }

    }
}

@Composable
fun CensoredText() {
    Box {
        Text(
            color = MaterialTheme.colorScheme.primary,
            lineHeight = 22.sp,
            text = "SUBJECT: OPERATION BLACKOUT\n DATE: 14 OCT 2024\n STATUS: CLASSIFIED\n" +
                    "\n" +
                    "Agent 24-G7 was deployed to the sector at 0400 hours. Initial contact with the local asset was established near the main terminal.\n" +
                    "The agent proceeded to the designated location and maintained communication with headquarters throughout the operation.\n" +
                    "Additional surveillance was conducted near .",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Black
        )

        // OPERATION BLACKOUT
        Box(
            modifier = Modifier
                .offset(0.dp, -4.dp)

                .width(145.dp)
                .height(20.dp)
                .background(Color.Black)
        )


        // Agent 24-G7
        Box(
            modifier = Modifier
                .offset(x = 32.dp, y = 95.dp)
                .width(150.dp)
                .height(20.dp)
                .background(Color.Black)
        )


        // main terminal
        Box(
            modifier = Modifier
                .offset(x = 200.dp, y = 120.dp)
                .width(115.dp)
                .height(20.dp)
                .background(Color.Black)
        )

        // Agent 24-G7 در خط بعدی
        Box(
            modifier = Modifier
                .offset(x = 0.dp, y = 142.dp)
                .width(110.dp)
                .height(20.dp)
                .background(Color.Black)
        )

        Box(
            modifier = Modifier
                .offset(x = 0.dp, y = 220.dp)
                .width(110.dp)
                .height(20.dp)
                .background(Color.Black)
        )
        Box(
            modifier = Modifier
                .offset(x = 120.dp, y = 195.dp)
                .width(110.dp)
                .height(20.dp)
                .background(Color.Black)
        )

    }
}

@Composable
fun SecretEnvelope(
    modifier: Modifier = Modifier,
    title: String = "SECRET ROLE",
    player: Player?,
    onNext: () -> Unit = {},
) {
    var isClosed by remember { mutableStateOf(true) }

    val flapHeight = 80.dp
    val envelopeHeight = 220.dp

    val flapProgress by animateFloatAsState(
        targetValue = if (isClosed) 0f else 1f,
        animationSpec = tween(
            durationMillis = 200,
            easing = LinearEasing
        ),
        label = "flapAnimation"
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(envelopeHeight + flapHeight)
            .clickable {
                isClosed = !isClosed

            }
    ) {

        // =================================================
        // بدنه پاکت
        // =================================================

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(envelopeHeight + flapHeight)
                .align(Alignment.Center)
        ) {

            val flap = flapHeight.toPx()
            val envelopeTop = flap

            // بدنه
            drawRoundRect(
                color = Color(0xFFF1EFE5),
                topLeft = Offset(
                    x = 0f,
                    y = envelopeTop
                ),
                size = Size(
                    width = size.width,
                    height = envelopeHeight.toPx()
                ),
                cornerRadius = CornerRadius(
                    8.dp.toPx()
                )
            )

            // حاشیه بدنه
            drawRoundRect(
                color = Color(0xFFB8B6AD),
                topLeft = Offset(
                    x = 0f,
                    y = envelopeTop
                ),
                size = Size(
                    width = size.width,
                    height = envelopeHeight.toPx()
                ),
                cornerRadius = CornerRadius(
                    8.dp.toPx()
                ),
                style = Stroke(
                    width = 2.dp.toPx()
                )
            )

            // =================================================
            // فلاپ
            // =================================================

            /*
             * بسته:
             *
             *      ▼
             *     / \
             *    /   \
             *   /     \
             *
             * باز:
             *
             *   \       /
             *    \     /
             *     \   /
             *      \ /
             *
             * در حالت باز، فلاپ به سمت بالا منتقل می‌شود.
             */

            val closedTipY = envelopeTop + flap

            val openTipY = envelopeTop - flap

            val tipY = closedTipY +
                    (openTipY - closedTipY) * flapProgress

            val animatedFlap = Path().apply {

                // گوشه چپ
                moveTo(
                    0f,
                    envelopeTop
                )

                // نوک فلاپ
                lineTo(
                    size.width / 2f,
                    tipY
                )

                // گوشه راست
                lineTo(
                    size.width,
                    envelopeTop
                )

                close()
            }

            // رنگ فلاپ
            drawPath(
                path = animatedFlap,
                color = Color(0xFFE8E5D9)
            )

            // حاشیه فلاپ
            drawPath(
                path = animatedFlap,
                color = Color(0xFFB8B6AD),
                style = Stroke(
                    width = 2.dp.toPx()
                )
            )
        }

        // =================================================
        // متن و بارکد
        // =================================================

        if (isClosed) {

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 35.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                BarcodePlaceholder()
            }

        } else {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 32.dp),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(top = 32.dp)
                ) {
                    HorizontalDivider(
                        color = Color(
                            0xFF3F51B5
                        ), thickness = 0.5.dp, modifier = Modifier.fillMaxWidth()
                    )

                    if (player?.role?.contains("جاسوس") == true) {
                        Text(
                            text = player?.role ?: "",
                            fontSize = 24.sp,
                            fontFamily = Traffic,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    } else {
                        Text(
                            text = ":کلمه رمز",
                            fontSize = 18.sp,
                            fontFamily = Traffic,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black.copy(0.5f)
                        )
                        Text(
                            text = player?.word ?: "",
                            fontSize = 24.sp,
                            fontFamily = Traffic,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black

                        )
                    }


                    HorizontalDivider(
                        color = Color(
                            0xFF3F51B5
                        ), thickness = 0.5.dp, modifier = Modifier.fillMaxWidth()
                    )
                    Button(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        onClick = { onNext(); isClosed = true }, shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                    ) {
                        Text(
                            text = "بعدی", fontSize = 16.sp,
                            fontWeight =
                                FontWeight.Light,
                            fontFamily = Traffic
                        )
                    }
                }
            }
        }
    }


}

@Composable
fun BarcodePlaceholder() {
    Canvas(
        modifier = Modifier

            .width(160.dp)
            .height(30.dp)
    ) {
        val bars = listOf(
            2, 1, 3, 1, 1, 2, 4, 1, 2, 1,
            3, 1, 1, 2, 2, 1, 4, 1, 2, 3,
            1, 2, 1, 3, 2, 1, 4, 1, 2, 1
        )

        var x = 100f

        bars.forEach { width ->
            drawRect(

                color = Color.Black,
                topLeft = Offset(x, 0f),
                size = Size(width.dp.toPx(), size.height)
            )

            x += (width + 1).dp.toPx()
        }
    }
}