package com.clove.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.ui.res.colorResource
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.clove.R


val JaldiFontFamily = FontFamily(
    Font(R.font.jaldi_regular, FontWeight.Normal),
    Font(R.font.jaldi_bold, FontWeight.Bold)
)

@Composable
fun WelcomeScreen(onContinueClick: () -> Unit = {}) {
    // Add an infinite transition for the animation
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.welcome_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()  // Ensures the column takes full width
                    .padding(40.dp), // Adjust the overall padding
                horizontalAlignment = Alignment.Start // Aligns text to the start (left side)
            ) {
                Text(
                    text = "Hey!",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontFamily = JaldiFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 40.sp,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(bottom = 2.dp) // Keeps a small space between texts
                )
                Text(
                    text = "Welcome to Clove",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontFamily = JaldiFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 40.sp,
                        color = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    // Ensures the text doesn't wrap
                    // This will now ensure that "Welcome to Clove" stays on a single line
                )
            }


            Spacer(modifier = Modifier.height(60.dp))

            // Meditation image and quote with animation
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(bottom = 100.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.clove_image),
                    contentDescription = "Meditation",
                    modifier = Modifier
                        .size(270.dp) // Adjust size as needed
                        .padding(top = 16.dp, bottom = 8.dp)
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale
                        )
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "\"Silence is healing.\"",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontFamily = JaldiFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            // Button
            Button(
                onClick = onContinueClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.btn),
                ),
                modifier = Modifier
                    .shadow(elevation = 8.dp, shape = MaterialTheme.shapes.medium)
                    .height(50.dp)
                    .width(200.dp)
                    .padding()
            ) {
                Text(text = "Get Started", color = Color.White, fontFamily = FontFamily.SansSerif)
            }
        }
    }
}




@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}
