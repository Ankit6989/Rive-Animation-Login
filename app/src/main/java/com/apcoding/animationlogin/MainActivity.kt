package com.apcoding.animationlogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.apcoding.animationlogin.ui.theme.AnimationLoginTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationLoginTheme {
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    // set transparent color so that our image is visible
                    // behind the status bar
                    systemUiController.setStatusBarColor(color = Color(0xFFd7e1e8))
                    systemUiController.setNavigationBarColor(
                        color = Color(0xFFd7e1e8)
                    )
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginUI()
                }
            }
        }
    }
}