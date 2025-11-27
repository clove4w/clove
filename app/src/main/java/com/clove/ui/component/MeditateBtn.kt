package com.clove.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.clove.R

@Composable
fun Meditate_button(onClick: () -> Unit = {}) {

    val boxShape = RoundedCornerShape(60.dp) // Define rounded corners for the box
    val borderColor = Color.White.copy(alpha = 0.5f) // Set the border color with 50% opacity

    val juaFontFamily = FontFamily(
        Font(R.font.jua_regular) // Reference to the custom font in res/font
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable(onClick = onClick) // Make the box clickable
            .clip(boxShape) // Apply rounded corners
            .border(3.dp, borderColor, boxShape) // Border with 50% opacity
    ) {
        // Image with opacity
        Image(
            painter = painterResource(id = R.drawable.meditate_btn_background), // Image resource
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize() // Make the image fill the entire box
                .graphicsLayer(alpha = 0.5f), // Apply opacity to the image
            contentScale = ContentScale.Crop // Ensure the image fills the area with cropping
        )

        // Text "Meditate" centered in the box

        Text(
            text = "Meditate",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontFamily = juaFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                color = Color.White
            ),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp) // Add padding here
                .align(Alignment.Center) // Center the text horizontally
        )
    }
}

@Preview
@Composable
fun Meditate_buttonPreview() {
    Meditate_button()
}
