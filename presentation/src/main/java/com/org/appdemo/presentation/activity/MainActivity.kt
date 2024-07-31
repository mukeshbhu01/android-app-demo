package com.org.appdemo.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.org.appdemo.presentation.navigation.AppNavigationGraph
import com.org.appdemo.presentation.ui.commonui.theme.DemoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoAppTheme {
                AppNavigationGraph(navHostController = rememberNavController())
            }
        }
    }
}
