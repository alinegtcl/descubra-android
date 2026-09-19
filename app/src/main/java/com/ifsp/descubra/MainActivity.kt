package com.ifsp.descubra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.ifsp.descubra.ui.theme.DescubraTheme
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    private val wikipediaApi = Retrofit.Builder()
        .baseUrl("https://pt.wikipedia.org/api/rest_v1/")
        .client(
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request()
                        .newBuilder()
                        .header(
                            "User-Agent",
                            "DescubraAndroid/1.0 (https://github.com/alinegtcl/descubra-android)"
                        )
                        .build()

                    chain.proceed(request)
                }
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WikipediaApi::class.java)

    private var articleTitle by mutableStateOf<String?>(null)
    private var articleDescription by mutableStateOf<String?>(null)
    private var isLoading by mutableStateOf(false)
    private var errorMessage by mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            DescubraTheme {
                DescubraScreen(
                    title = articleTitle,
                    description = articleDescription,
                    isLoading = isLoading,
                    errorMessage = errorMessage,
                    onDiscover = ::discoverArticle
                )
            }
        }
    }

    private fun discoverArticle() {
        lifecycleScope.launch {
            isLoading = true
            errorMessage = null
            articleTitle = null
            articleDescription = null

            try {
                val response = wikipediaApi.getRandomArticle()

                articleTitle = response.title
                articleDescription = response.extract

            } catch (exception: Exception) {
                errorMessage = "Não foi possível descobrir algo novo. Tente novamente."
            } finally {
                isLoading = false
            }
        }
    }
}