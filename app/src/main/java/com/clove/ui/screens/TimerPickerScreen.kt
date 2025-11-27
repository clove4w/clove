package com.clove.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clove.R

@Composable
fun TimerPickerScreen(onTimerSelected: (Int) -> Unit = {}) {
    val selectedTimer = remember { mutableStateOf<Int?>(null) }
    val timerOptions = listOf(2, 5, 10, 15, 20, 30)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.primary_purple))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "Set Timer",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    color = Color.White
                ),
                modifier = Modifier.padding(bottom = 40.dp)
            )

            // Timer options box
            Box(
                modifier = Modifier
                    .background(
                        color = Color(0xFFB8A0D1),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    timerOptions.forEach { minutes ->
                        TimerOptionButton(
                            minutes = minutes,
                            isSelected = selectedTimer.value == minutes,
                            onSelect = { selectedTimer.value = it }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Meditate Button
            Button(
                onClick = {
                    selectedTimer.value?.let { onTimerSelected(it) }
                },
                enabled = selectedTimer.value != null,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.button_pink),
                    disabledContainerColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(
                    text = "Meditate",
                    color = Color.Black,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun TimerOptionButton(
    minutes: Int,
    isSelected: Boolean,
    onSelect: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (isSelected) Color(0xFFB8A0D1) else Color(0xFFD9C8E8),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onSelect(minutes) }
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$minutes min",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = if (isSelected) Color.White else Color.DarkGray
            )
        )
    }
}

@Preview
@Composable
fun TimerPickerScreenPreview() {
    TimerPickerScreen()
}
