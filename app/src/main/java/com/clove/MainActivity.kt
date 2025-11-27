package com.clove

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.clove.ui.theme.CloveTheme
import com.clove.utils.AppPreferences

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val appPreferences = AppPreferences(this)

        setContent {
            CloveTheme {
                if (appPreferences.isFirstLaunch()) {
                    // Show onboarding flow
                    OnboardingFlow(appPreferences = appPreferences)
                } else {
                    // Show main app with navigation
                    MainScreen()
                }
            }
        }
    }
}
