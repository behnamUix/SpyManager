package com.behnamuix.spygame.feature.configgame.presentation.screen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.Bitmap
import coil3.compose.AsyncImage
import com.behnamuix.appointment.const.BACKGROUND_URL
import com.behnamuix.spygame.R
import com.behnamuix.spygame.core.theme.AppDimens
import com.behnamuix.spygame.core.theme.AppShapes
import com.behnamuix.spygame.feature.configgame.presentation.contract.ConfigGameContract
import com.behnamuix.spygame.feature.configgame.presentation.screen.components.IconISwitch
import com.behnamuix.spygame.feature.configgame.presentation.viewmodel.ConfigGameViewModel
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import okhttp3.internal.userAgent


@Composable
fun ConfigGameSc(
    vm: ConfigGameViewModel = hiltViewModel(),
    OpenConfigRoleClicked: () -> Unit,
) {


    ConfigGameContent(vm, OpenConfigRoleClicked)
}

@Composable
fun ConfigGameContent(vm: ConfigGameViewModel, OpenConfigRoleClicked: () -> Unit) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFBE9A73))
    ) {

        val configGameState = vm.configGameState
            .collectAsStateWithLifecycle()
        LaunchedEffect(Unit) {

            vm.onAction(ConfigGameContract.ConfigGameAction.setBiometricProgress)


        }

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

            Spacer(
                modifier = Modifier.height(
                    50.dp
                )
            )

            Text(
                text = "CONFIDENTIAL INFORMATION",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(
                    AppDimens.contentTopSpace
                )
            )

            CountCard(

                configGameState = configGameState,
                title = "AGENT COUNT",
                count = configGameState.value.agentCount,
                onDecrease = {
                    vm.onAction(
                        ConfigGameContract.ConfigGameAction
                            .DecreaseAgentCount
                    )
                }
            ) {
                vm.onAction(
                    ConfigGameContract.ConfigGameAction
                        .IncreaseAgentCount
                )
            }

            Spacer(
                modifier = Modifier.height(
                    AppDimens.screenPadding
                )
            )

            CountCard(

                configGameState,
                title = "SPY COUNT",
                count = configGameState.value.spyCount,
                iconTint = MaterialTheme.colorScheme.error,
                onDecrease = {
                    vm.onAction(
                        ConfigGameContract.ConfigGameAction
                            .DecreaseSpyCount
                    )
                },
                onIncrease = {
                    vm.onAction(
                        ConfigGameContract.ConfigGameAction
                            .IncreaseSpyCount
                    )
                }
            )
            Spacer(
                modifier = Modifier.height(
                    AppDimens.spacingXl
                )
            )
            AiCard(configGameState)
            Box(
                modifier = Modifier
                    .padding(top = 24.dp)
            ) {
                Button(
                    onClick = {
                        OpenConfigRoleClicked()
                    },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                ) {
                    Text(
                        "RUN TURN",
                        color = MaterialTheme.colorScheme.outline,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Black
                    )
                }
            }

        }
    }

}


@Composable
fun CountCard(
    configGameState: State<ConfigGameContract.ConfigGameState>,
    title: String,
    count: Int,
    iconTint: Color = Color.Unspecified,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = AppDimens.borderWidth,
                color = Color.Black,
                shape = MaterialTheme.shapes.large
            )
    ) {
        Column(
            modifier = Modifier.padding(AppDimens.screenPadding)
        ) {

            Text(
                text = title,
                color = Color.Black,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            HorizontalDivider(
                modifier = Modifier.padding(
                    top = AppDimens.dividerTopPadding
                )
            )

            Row(
                modifier = Modifier.padding(
                    top = AppDimens.dividerTopPadding
                )
            ) {

                Icon(
                    painter = painterResource(R.drawable.icon_spy),
                    tint = iconTint,
                    contentDescription = null,
                    modifier = Modifier
                        .size(AppDimens.iconHero)
                        .border(
                            width = AppDimens.borderWidth,
                            color = Color.Black,
                            shape = MaterialTheme.shapes.large
                        )
                )

                Column(
                    modifier = Modifier.padding(
                        start = AppDimens.screenPadding
                    )
                ) {
                    Row {
                        Text(
                            text = "Code:",
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = "24G7",
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    Column {
                        Text(
                            text = "Status:",
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = "Infiltered",
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(
                        start = AppDimens.screenPadding
                    )
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(
                            AppDimens.spacingMedium
                        )
                    ) {

                        Box(
                            modifier = Modifier
                                .size(AppDimens.spacingXl)
                                .border(
                                    width = 1.dp,
                                    color = Color.Black,
                                    shape = AppShapes.small
                                )
                                .clickable {
                                    onDecrease()
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                tint = Color.Black,
                                modifier = Modifier.size(AppDimens.iconMedium),
                                painter = painterResource(
                                    R.drawable.icon_minus
                                ),
                                contentDescription = "Decrease count"
                            )
                        }

                        Text(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            text = count.toString(),
                            style = MaterialTheme.typography.headlineMedium
                        )

                        Box(
                            modifier = Modifier
                                .size(AppDimens.spacingXl)
                                .border(
                                    width = 1.dp,
                                    color = Color.Black,
                                    shape = AppShapes.small
                                )
                                .clickable {
                                    onIncrease()
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                tint = Color.Black,
                                modifier = Modifier.size(AppDimens.iconMedium),
                                painter = painterResource(
                                    R.drawable.icon_plus
                                ),
                                contentDescription = "Increase count"
                            )
                        }
                    }
                    Image(
                        bitmap = BarcodeGeneratorComp(
                            value = (count * 100).toString(),
                            format = BarcodeFormat.CODE_128,
                            width = 350,
                            height = 50
                        ).asImageBitmap(),
                        contentDescription = "Barcode",
                        modifier = Modifier
                            .width(350.dp)
                            .height(50.dp)
                    )


                }
            }
        }
    }
}

@Composable
fun BarcodeGeneratorComp(
    value: String,
    format: BarcodeFormat,
    width: Int,
    height: Int
): Bitmap {

    val encoder = BarcodeEncoder()
    val bitmap = encoder.encodeBitmap(
        value,
        format,
        width,
        height
    )
    bitmap.apply {
        for (x in 0 until width) {
            for (y in 0 until height) {
                if (getPixel(x, y) == android.graphics.Color.WHITE) {
                    setPixel(
                        x,
                        y,
                        android.graphics.Color.TRANSPARENT
                    )
                }
            }
        }
    }
    return bitmap
}

@Composable
fun AiCard(

    configGameState: State<ConfigGameContract.ConfigGameState>,
    vm: ConfigGameViewModel = hiltViewModel(),
) {
    var check by remember { mutableStateOf(false) }
    val animatedProgress by animateFloatAsState(
        targetValue = configGameState.value.biometricSyncProg,
        animationSpec = tween(
            durationMillis = 1200,
            easing = FastOutSlowInEasing
        ),
        label = "progress"
    )
    val infiniteTransition = rememberInfiniteTransition(
        label = "laser"
    )

    val laserAnimateFinger by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2600,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "finger_laser"
    )

    val laserAnimateHand by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2200,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "hand_laser"
    )
    Box(
        modifier = Modifier

            .fillMaxWidth()
            .border(
                width = AppDimens.borderWidth,
                color = Color.Black,
                shape = MaterialTheme.shapes.large
            )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(AppDimens.screenPadding)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ScanningIcon(

                    useAnim=true,
                    imageRes = R.drawable.fingerprint,
                    laserAnimate = laserAnimateFinger, useAnim = true
                )

                ScanningIcon(
                    useAnim=false,
                    imageRes = R.drawable.handprint,
                    laserAnimate = laserAnimateHand,
                    useAnim = false
                )
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(

                        text = "BIOMETRIC SYNC:${(configGameState.value.biometricSyncProg * 100).toInt()}%",
                        letterSpacing = TextUnit(0.4f, TextUnitType.Sp),
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.Black
                    )


                    LinearProgressIndicator(

                        progress = { animatedProgress },
                        modifier = Modifier
                            .height(20.dp)
                            .clip(RectangleShape)
                            .border(1.dp, Color.Black, shape = RoundedCornerShape(4.dp)),
                        color = Color.Black,
                        trackColor = Color.Transparent,

                        strokeCap = StrokeCap.Butt,
                    )
                    Text(

                        text = "DATA VERIFIED",
                        letterSpacing = TextUnit(0.8f, TextUnitType.Sp),
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.Black
                    )
                    Text(


                        text = "DATA VERIFIED",
                        fontWeight = FontWeight.Bold,
                        letterSpacing = TextUnit(0.8f, TextUnitType.Sp),
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.Black
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(
                    top = AppDimens.dividerTopPadding
                )
            )
            OutlinedTextField(
                enabled = configGameState.value.useAi,
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        text = "example word",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray
                    )
                },
                trailingIcon = {
                    Box(
                        modifier = Modifier
                            .alpha(
                                if (configGameState.value.useAi) {
                                    1f
                                } else {
                                    0f
                                }
                            )
                            .padding(6.dp)
                            .size(32.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(7.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(7.dp)
                            )
                            .clickable {},
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            tint = Color.Black,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(46.dp),
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,

                    focusedPlaceholderColor = Color.Gray,
                    unfocusedPlaceholderColor = Color.Gray,

                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,

                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,

                    cursorColor = Color.Black,

                    focusedTrailingIconColor = Color.Black,
                    unfocusedTrailingIconColor = Color.Black
                ),
                singleLine = true
            )

            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("USE AI:")
                Spacer(modifier = Modifier.width(32.dp))
                IconISwitch(checked = check, onCheckedChange = {
                    check = it
                    vm.onAction(ConfigGameContract.ConfigGameAction.setSetAiSwitch(it))
                })
            }


        }
    }
}

@Composable
fun ScanningIcon(
    useAnim: Boolean,
    imageRes: Int,
    laserAnimate: Float,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier.size(70.dp)
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            modifier = Modifier.size(70.dp)
        )
        if(useAnim){

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp)
                    .offset(y=y)
                    .background(
                        color = Color(0xFF00E5FF),
                        shape = RoundedCornerShape(50)
                    )
                    .dropShadow(
                        shape = RoundedCornerShape(50),
                        shadow = Shadow(
                            radius = 14.dp,
                            color = Color(0xFF00E5FF),
                            spread = 2.dp,
                            offset = DpOffset(0.dp, 0.dp),
                            alpha = 0.8f
                        )
                    )
                    .dropShadow(
                        shape = RoundedCornerShape(50),
                        shadow = Shadow(
                            radius = 28.dp,
                            color = Color(0xFF00B8FF),
                            spread = 4.dp,
                            offset = DpOffset(0.dp, 0.dp),
                            alpha = 0.35f
                        )
                    )
            )
        }
    }
}

