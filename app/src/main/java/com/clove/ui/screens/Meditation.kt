package com.clove.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.clove.R
import com.clove.utils.MeditationTimer

@Composable
fun Meditation(navController: NavController? = null, timerDuration: Int = 0) {
    val timer = remember { MeditationTimer() }
    val coroutineScope = rememberCoroutineScope()
    val isMeditating = remember { mutableStateOf(false) }

    LaunchedEffect(timerDuration) {
        if (timerDuration > 0 && !isMeditating.value) {
            timer.startTimer(timerDuration, coroutineScope)
            isMeditating.value = true
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(colorResource(id = R.color.primary_purple))) {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            // Title: FOCUS
            Text(
                text = "FOCUS",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 48.sp,
                    color = Color.White
                )
            )

            // Timer Display (shown when meditating)
            if (isMeditating.value && timerDuration > 0) {
                Text(
                    text = timer.formatTime(timer.remainingTime.value),
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 72.sp,
                        color = colorResource(id = R.color.meditation_pink)
                    )
                )
            }

            // Meditation Circle with Concentric Design
            Box(
                modifier = Modifier
                    .width(220.dp)
                    .height(220.dp),
                contentAlignment = Alignment.Center
            ) {
                // Outer circle
                Box(
                    modifier = Modifier
                        .size(220.dp)
                        .clip(CircleShape)
                        .background(colorResource(id = R.color.meditation_pink))
                )

                // Middle circle
                Box(
                    modifier = Modifier
                        .size(180.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFD4B5D0))
                )

                // Inner circle with meditation pose
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                        .background(colorResource(id = R.color.meditation_pink)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "🧘",
                        fontSize = 80.sp
                    )
                }
            }

            // Buttons Column
            Column(
                modifier = Modifier.padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (!isMeditating.value || timerDuration == 0) {
                    // Set Timer Button
                    Button(
                        onClick = { navController?.navigate("timer_picker") },
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.button_pink)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(25.dp)
                    ) {
                        Text(
                            text = "Set Timer",
                            color = Color.Black,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    }
                }

                // Main Action Buttons
                if (isMeditating.value && timerDuration > 0 && timer.remainingTime.value > 0) {
                    // Pause/Resume Row
                    androidx.compose.foundation.layout.Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = {
                                if (timer.isRunning.value) {
                                    timer.pauseTimer()
                                } else {
                                    timer.resumeTimer(coroutineScope)
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.accent_blue)
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp),
                            shape = RoundedCornerShape(25.dp)
                        ) {
                            Text(
                                text = if (timer.isRunning.value) "Pause" else "Resume",
                                color = Color.White,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        }

                        Button(
                            onClick = {
                                timer.stopTimer()
                                isMeditating.value = false
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.accent_pink)),
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp),
                            shape = RoundedCornerShape(25.dp)
                        ) {
                            Text(
                                text = "Stop",
                                color = Color.White,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        }
                    }
                } else {
                    // Start Meditation Button
                    Button(
                        onClick = {
                            if (timerDuration > 0) {
                                timer.startTimer(timerDuration, coroutineScope)
                                isMeditating.value = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.accent_yellow),
                            disabledContainerColor = Color.Gray
                        ),
                        enabled = timerDuration > 0,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(25.dp)
                    ) {
                        Text(
                            text = "Start Meditation",
                            color = Color.Black,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }

}

@Preview
@Composable
fun MeditationPreview(){
    Meditation()
}
