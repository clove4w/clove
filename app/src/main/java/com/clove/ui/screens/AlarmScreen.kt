package com.clove.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import com.clove.R
import com.clove.data.MeditationAlarm
import com.clove.utils.AppPreferences

@Composable
fun AlarmScreen(navController: NavController? = null) {
    val context = LocalContext.current
    val appPreferences = remember { AppPreferences(context) }
    val selectedHour = remember { mutableStateOf(9) }
    val selectedMinute = remember { mutableStateOf(0) }
    val selectedPeriod = remember { mutableStateOf("am") }
    val alarms = remember { mutableStateOf(appPreferences.getAlarms()) }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.alarm_screen_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp, bottom = 100.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // Header Section
            item {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Set Alarm",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        color = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                Text(
                    text = "Choose when to meditate",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.6f)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp)
                )
            }

            // Time Picker Card
            item {
                AlarmTimePickerCard(
                    hour = selectedHour.value,
                    minute = selectedMinute.value,
                    period = selectedPeriod.value,
                    onHourChange = { selectedHour.value = it },
                    onMinuteChange = { selectedMinute.value = it },
                    onPeriodChange = { selectedPeriod.value = it }
                )
                Spacer(modifier = Modifier.height(32.dp))
            }

            // Set Button
            item {
                Button(
                    onClick = {
                        val hour24 = when {
                            selectedPeriod.value == "pm" && selectedHour.value != 12 -> selectedHour.value + 12
                            selectedPeriod.value == "am" && selectedHour.value == 12 -> 0
                            else -> selectedHour.value
                        }

                        val newAlarm = MeditationAlarm(
                            id = System.currentTimeMillis().toString(),
                            hour = hour24,
                            minute = selectedMinute.value,
                            label = "Meditate"
                        )
                        alarms.value = alarms.value + newAlarm
                        appPreferences.saveAlarms(alarms.value)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.accent_yellow)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(28.dp)
                ) {
                    Text(
                        text = "SET ALARM",
                        color = Color.Black,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
            }

            // Active Alarms Section Header
            if (alarms.value.isNotEmpty()) {
                item {
                    Text(
                        text = "Active Alarms",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.White
                        ),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                // Alarm Items
                items(alarms.value) { alarm ->
                    SavedAlarmItem(
                        alarm = alarm,
                        onDelete = {
                            alarms.value = alarms.value.filter { it.id != alarm.id }
                            appPreferences.saveAlarms(alarms.value)
                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
    }
}

@Composable
fun AlarmTimePickerCard(
    hour: Int,
    minute: Int,
    period: String,
    onHourChange: (Int) -> Unit,
    onMinuteChange: (Int) -> Unit,
    onPeriodChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White.copy(alpha = 0.95f),
                shape = RoundedCornerShape(28.dp)
            )
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Big Time Display
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.primary_purple).copy(alpha = 0.1f),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(vertical = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = String.format("%02d", hour),
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 56.sp,
                        color = colorResource(id = R.color.button_pink)
                    )
                )
                Text(
                    text = ":",
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 48.sp,
                        color = colorResource(id = R.color.button_pink)
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Text(
                    text = String.format("%02d", minute),
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 56.sp,
                        color = colorResource(id = R.color.button_pink)
                    )
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = period.uppercase(),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        color = colorResource(id = R.color.primary_purple)
                    )
                )
            }
        }

        // Time Pickers Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Hour Picker
            VerticalTimePicker(
                items = (1..12).map { String.format("%02d", it) },
                selectedIndex = hour - 1,
                onSelect = { onHourChange(it + 1) },
                modifier = Modifier.weight(1f)
            )

            // Colon separator
            Text(
                text = ":",
                style = MaterialTheme.typography.displayMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    color = colorResource(id = R.color.button_pink)
                )
            )

            // Minute Picker
            VerticalTimePicker(
                items = (0..59).map { String.format("%02d", it) },
                selectedIndex = minute,
                onSelect = { onMinuteChange(it) },
                modifier = Modifier.weight(1f)
            )

            // Period Picker
            PeriodButtonGroup(
                selected = period,
                onSelect = onPeriodChange,
                modifier = Modifier.width(60.dp)
            )
        }
    }
}

@Composable
fun VerticalTimePicker(
    items: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            )
            .border(2.dp, colorResource(id = R.color.button_pink), RoundedCornerShape(12.dp))
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            items(items.size) { index ->
                val isSelected = index == selectedIndex
                Text(
                    text = items[index],
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        fontSize = if (isSelected) 24.sp else 18.sp,
                        color = if (isSelected) colorResource(id = R.color.button_pink) else Color.LightGray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelect(index) }
                        .padding(vertical = 6.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun PeriodButtonGroup(
    selected: String,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = colorResource(id = R.color.button_pink),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        listOf("am", "pm").forEach { period ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = if (selected == period) Color.White else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { onSelect(period) }
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = period.uppercase(),
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = if (selected == period) colorResource(id = R.color.button_pink) else Color.White
                    )
                )
            }
        }
    }
}


@Composable
fun SavedAlarmItem(
    alarm: MeditationAlarm,
    onDelete: () -> Unit
) {
    // Convert 24-hour to 12-hour format
    val hour12 = if (alarm.hour == 0) 12 else if (alarm.hour > 12) alarm.hour - 12 else alarm.hour
    val period = if (alarm.hour >= 12) "PM" else "AM"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White.copy(alpha = 0.92f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Time Display Circle
        Box(
            modifier = Modifier
                .size(70.dp)
                .background(
                    color = colorResource(id = R.color.button_pink),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = String.format("%02d:%02d", hour12, alarm.minute),
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                )
                Text(
                    text = period,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                )
            }
        }

        // Label
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Meditation Alarm",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.primary_purple)
                )
            )
            Text(
                text = "Set to meditate",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 12.sp,
                    color = Color.Gray
                ),
                modifier = Modifier.padding(top = 2.dp)
            )
        }

        // Delete button
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = colorResource(id = R.color.accent_pink).copy(alpha = 0.15f),
                    shape = CircleShape
                )
                .clickable { onDelete() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_alarm),
                contentDescription = "Delete Alarm",
                modifier = Modifier.size(20.dp),
                tint = colorResource(id = R.color.accent_pink)
            )
        }
    }
}

@Preview
@Composable
fun AlarmScreenPreview() {
    AlarmScreen()
}
