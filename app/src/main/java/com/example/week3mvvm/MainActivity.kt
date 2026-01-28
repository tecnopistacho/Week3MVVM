package com.example.week3mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.week3mvvm.ui.theme.Week3MVVMTheme
import com.example.week3mvvm.view.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week3MVVMTheme() {
                HomeScreen()
            }
        }
    }
}