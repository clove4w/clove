package com.clove.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clove.R

@Composable
fun ReminderScreen(onTimeSelected: (String) -> Unit = {}, onBack: () -> Unit = {}) {
    val selectedTime = remember { mutableStateOf<String?>(null) }
    val hour = remember { mutableStateOf(9) }
    val minute = remember { mutableStateOf(0) }
    val isPM = remember { mutableStateOf(false) }

    fun formatTime(h: Int, m: Int, pm: Boolean): String {
        val period = if (pm) "PM" else "AM"
        return String.format("%02d:%02d %s", h, m, period)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.primary_purple))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Back Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_home),
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { onBack() },
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Question Title
            Text(
                text = "What time you want\nto Meditate?",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    color = Color.White
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 40.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Time display
            Box(
                modifier = Modifier
                    .background(
                        color = Color(0xFF9B8BB3),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = formatTime(hour.value, minute.value, isPM.value),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp,
                        color = Color.White
                    )
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Time picker controls
            Box(
                modifier = Modifier
                    .background(
                        color = Color(0xFFB8A0D1),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Hour Picker
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = { if (hour.value > 1) hour.value-- else hour.value = 12 },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9B8BB3)),
                            modifier = Modifier.size(40.dp)
                        ) { Text("-", color = Color.White) }

                        Text(
                            text = String.format("%02d", hour.value),
                            style = MaterialTheme.typography.headlineSmall.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )

                        Button(
                            onClick = { if (hour.value < 12) hour.value++ else hour.value = 1 },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9B8BB3)),
                            modifier = Modifier.size(40.dp)
                        ) { Text("+", color = Color.White) }
                    }

                    // Minute Picker
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = { minute.value = if (minute.value > 0) minute.value - 5 else 55 },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9B8BB3)),
                            modifier = Modifier.size(40.dp)
                        ) { Text("-", color = Color.White) }

                        Text(
                            text = String.format("%02d", minute.value),
                            style = MaterialTheme.typography.headlineSmall.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )

                        Button(
                            onClick = { minute.value = if (minute.value < 55) minute.value + 5 else 0 },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9B8BB3)),
                            modifier = Modifier.size(40.dp)
                        ) { Text("+", color = Color.White) }
                    }

                    // AM/PM Toggle
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = { isPM.value = !isPM.value },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isPM.value) Color(0xFF7A6B7E) else colorResource(id = R.color.accent_blue)
                            ),
                            modifier = Modifier
                                .width(100.dp)
                                .height(40.dp)
                        ) {
                            Text(
                                text = if (isPM.value) "PM" else "AM",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(80.dp))

            // Save Button
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Button(
                    onClick = {
                        onTimeSelected(formatTime(hour.value, minute.value, isPM.value))
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.accent_blue)
                    ),
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                        .size(width = 100.dp, height = 50.dp),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Text(
                        text = "SAVE",
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ReminderScreenPreview() {
    ReminderScreen()
}
