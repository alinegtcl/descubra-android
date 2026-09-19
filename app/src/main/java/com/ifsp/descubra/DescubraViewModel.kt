package com.ifsp.descubra

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DescubraViewModel : ViewModel() {
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

    var articleTitle by mutableStateOf<String?>(null)
    var articleDescription by mutableStateOf<String?>(null)
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    fun discoverArticle() {
        viewModelScope.launch {
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