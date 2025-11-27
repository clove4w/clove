package com.clove.ui.screens

import ClickablePngIcon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import coil.compose.rememberAsyncImagePainter
import com.clove.R
import com.clove.ui.component.Meditate_button
import com.clove.ui.component.MoodInputBox
import com.clove.ui.component.Sleep_Sound_button
import com.clove.utils.AppPreferences
import com.example.meditationapp.ui.components.ProfilePicture
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(navController: NavController? = null) {
    val context = LocalContext.current
    val appPreferences = remember { AppPreferences(context) }
    val userName = remember { mutableStateOf(appPreferences.getUserName()) }
    val profilePhotoUri = remember { mutableStateOf(appPreferences.getProfilePhotoUri()) }

    val upcomingAlarm = remember { mutableStateOf(appPreferences.getNextUpcomingAlarm()) }
    val countdownTime = remember { mutableStateOf("") }

    // Update countdown every second
    LaunchedEffect(upcomingAlarm.value) {
        while (upcomingAlarm.value != null) {
            val alarm = upcomingAlarm.value ?: break
            val currentHour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
            val currentMinute = java.util.Calendar.getInstance().get(java.util.Calendar.MINUTE)
            val currentSecond = java.util.Calendar.getInstance().get(java.util.Calendar.SECOND)

            val alarmTotalMinutes = alarm.hour * 60 + alarm.minute
            val currentTotalSeconds = currentHour * 3600 + currentMinute * 60 + currentSecond

            val remainingSeconds = if (alarmTotalMinutes * 60 > currentTotalSeconds) {
                alarmTotalMinutes * 60 - currentTotalSeconds
            } else {
                // Alarm is for tomorrow
                (24 * 3600) - currentTotalSeconds + (alarmTotalMinutes * 60)
            }

            val hours = remainingSeconds / 3600
            val minutes = (remainingSeconds % 3600) / 60
            val seconds = remainingSeconds % 60

            countdownTime.value = String.format("%02d:%02d:%02d", hours, minutes, seconds)

            delay(1000L)
        }
    }
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.homescreen_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            // Top Row: Profile and Add Icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                // Profile Picture
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFD4B5D0)),
                    contentAlignment = Alignment.Center
                ) {
                    if (profilePhotoUri.value != null) {
                        Image(
                            painter = rememberAsyncImagePainter(model = profilePhotoUri.value),
                            contentDescription = "Profile Photo",
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Text("👤", fontSize = 28.sp)
                    }
                }
                IconScreen()
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Greeting and Timer Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ){
                Column(
                    verticalArrangement = Arrangement.spacedBy(0.dp)
                ){
                    Text(
                        text = "Good Morning",
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp,
                        color = Color.White
                    )

                    Text(
                        text = userName.value.uppercase(),
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        color = Color.White
                    )
                }

                // Timer box - Meditation Reminder
                if (upcomingAlarm.value != null) {
                    Box(
                        modifier = Modifier
                            .width(140.dp)
                            .background(
                                color = colorResource(id = R.color.button_pink),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Meditation Starts in :",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black
                                ),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = countdownTime.value.ifEmpty { "00:00:00" },
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.DarkGray
                                ),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Mood Input Box
            MoodInputBox(
                onStatsClick = {
                    navController?.navigate("stats")
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Buttons Column
            Column(modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()){
                Meditate_button(onClick = {
                    navController?.navigate("meditation_focus")
                })
                Spacer(modifier = Modifier.height(32.dp))
                Sleep_Sound_button()
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}

@Composable
fun IconScreen() {
    ClickablePngIcon(
        iconResource = R.drawable.ic_add, // Replace with your PNG resource
        contentDescription = "My PNG Icon",
        onClick = {
            // Perform your action here
            println("PNG Icon clicked!")
        }
    )
}

