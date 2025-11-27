package com.clove.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clove.R
import com.clove.utils.AppPreferences

@Composable
fun MoodInputBox(onStatsClick: () -> Unit = {}) {
    val context = LocalContext.current
    val appPreferences = remember { AppPreferences(context) }
    val userInput = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(0.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            // User Input Field
            OutlinedTextField(
                value = userInput.value,
                onValueChange = {
                    userInput.value = it
                },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .onKeyEvent { keyEvent ->
                        if (keyEvent.key == Key.Enter) {
                            if (userInput.value.isNotEmpty()) {
                                appPreferences.saveMoodEntry(userInput.value)
                                userInput.value = ""
                            }
                            true
                        } else {
                            false
                        }
                    },
                shape = RoundedCornerShape(16.dp),
                placeholder = {
                    Text(
                        text = "What's on your mind?",
                        style = TextStyle(
                            textAlign = TextAlign.Start,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    )
                },
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (userInput.value.isNotEmpty()) {
                            appPreferences.saveMoodEntry(userInput.value)
                            userInput.value = ""
                        }
                    }
                )
            )

            // Stats Icon Button (Book Icon)
            IconButton(
                onClick = onStatsClick,
                modifier = Modifier.height(56.dp)
            ) {
                Text(
                    text = "📖",
                    fontSize = 24.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun MoodInputBoxPreview() {
    MoodInputBox()
}
