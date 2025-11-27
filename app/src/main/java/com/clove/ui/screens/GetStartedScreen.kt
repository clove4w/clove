package com.clove.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clove.R
import com.clove.ui.component.ProfilePhotoPicker
import com.example.meditationapp.ui.components.ProfilePicture

@Composable
fun GetStartedScreen(onCompleteClick: (String, String?) -> Unit = { _, _ -> }) {
    val userName = remember { mutableStateOf("") }
    val profilePhotoUri = remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.primary_purple))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Photo Picker
            ProfilePhotoPicker(
                currentPhotoUri = profilePhotoUri.value,
                onPhotoSelected = { uri ->
                    profilePhotoUri.value = uri
                }
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Welcome Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(25.dp)
                    )
                    .padding(horizontal = 24.dp, vertical = 32.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Name input field
                    TextField(
                        value = userName.value,
                        onValueChange = { userName.value = it },
                        placeholder = {
                            Text(
                                text = "Name",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontSize = 18.sp,
                                    color = Color.LightGray
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White),
                        textStyle = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 18.sp,
                            color = Color.Black
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = Color.Black,
                            unfocusedIndicatorColor = Color.Gray
                        ),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Save Button
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Button(
                            onClick = {
                                if (userName.value.isNotEmpty()) {
                                    onCompleteClick(userName.value, profilePhotoUri.value)
                                }
                            },
                            enabled = userName.value.isNotEmpty(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.accent_blue),
                                disabledContainerColor = Color.Gray
                            ),
                            modifier = Modifier
                                .size(width = 100.dp, height = 45.dp),
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

            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Preview
@Composable
fun GetStartedScreenPreview() {
    GetStartedScreen()
}
