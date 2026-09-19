package com.ifsp.descubra.data.repository

import com.ifsp.descubra.data.remote.WikipediaService
import com.ifsp.descubra.domain.model.Article

class ArticleRepositoryImpl(
    private val wikipediaService: WikipediaService
) : ArticleRepository {

    override suspend fun getRandomArticle(): Article {
        val response = wikipediaService.getRandomArticle()
        return Article(
            title = response.title,
            description = response.extract
        )
    }
}