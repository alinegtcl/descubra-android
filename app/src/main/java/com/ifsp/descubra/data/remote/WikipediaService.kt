package com.ifsp.descubra.data.remote

interface WikipediaService {

    suspend fun getRandomArticle(): WikipediaResponse
}