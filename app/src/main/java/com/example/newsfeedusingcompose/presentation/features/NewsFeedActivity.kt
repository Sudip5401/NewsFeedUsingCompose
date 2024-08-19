package com.example.newsfeedusingcompose.presentation.features

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeinitial.nestednavigation.SetupNavGraph
import com.example.newsfeedusingcompose.presentation.core.theme.NewsFeedUsingComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFeedActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER
        }
        setContent {
            NewsFeedUsingComposeTheme {
                Routing()
            }
        }
    }

    @Composable
    private fun Routing() {
        val navigationController = rememberNavController()
        SetupNavGraph(navigationController)
    }
}