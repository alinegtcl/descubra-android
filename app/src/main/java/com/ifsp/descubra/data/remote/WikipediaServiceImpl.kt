package com.ifsp.descubra.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WikipediaServiceImpl : WikipediaService {

    private val api = Retrofit.Builder()
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

    override suspend fun getRandomArticle(): WikipediaResponse {
        return api.getRandomArticle()
    }
}