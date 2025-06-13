package com.dyah0134.dinosaurus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.dyah0134.dinosaurus.ui.screen.MainScreen
import com.dyah0134.dinosaurus.ui.theme.DinosaurusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DinosaurusTheme {
                MainScreen()
            }
        }
    }
}
