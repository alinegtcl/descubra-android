package com.ifsp.descubra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ifsp.descubra.ui.theme.DescubraTheme

class MainActivity : ComponentActivity() {

    private val viewModel = DescubraViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            DescubraTheme {
                DescubraScreen(
                    title = viewModel.articleTitle,
                    description = viewModel.articleDescription,
                    isLoading = viewModel.isLoading,
                    errorMessage = viewModel.errorMessage,
                    onDiscover = { viewModel.discoverArticle() }
                )
            }
        }
    }
}