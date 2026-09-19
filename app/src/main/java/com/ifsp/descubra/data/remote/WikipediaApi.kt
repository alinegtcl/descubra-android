package com.ifsp.descubra.data.remote

import retrofit2.http.GET

interface WikipediaApi {

    @GET("page/random/summary")
    suspend fun getRandomArticle(): WikipediaResponse
}