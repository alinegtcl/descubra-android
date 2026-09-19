package com.ifsp.descubra.data.repository

import com.ifsp.descubra.data.remote.WikipediaResponse

interface ArticleRepository {

    suspend fun getRandomArticle(): WikipediaResponse
}