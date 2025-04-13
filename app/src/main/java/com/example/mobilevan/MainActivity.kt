package com.example.mobilevan


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mobilevan.ui.theme.MobileVanTheme
import com.example.mobilevan.ui.navigation.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileVanTheme {
                Navigation()
            }
        }
    }
}
