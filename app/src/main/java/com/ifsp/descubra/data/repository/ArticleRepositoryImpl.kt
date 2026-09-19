package com.ifsp.descubra.data.repository

import com.ifsp.descubra.data.remote.WikipediaResponse
import com.ifsp.descubra.data.remote.WikipediaService

class ArticleRepositoryImpl(
    private val wikipediaService: WikipediaService
) : ArticleRepository {

    override suspend fun getRandomArticle(): WikipediaResponse {
        return wikipediaService.getRandomArticle()
    }
}