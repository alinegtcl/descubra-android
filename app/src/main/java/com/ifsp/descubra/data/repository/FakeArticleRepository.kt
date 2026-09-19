package com.ifsp.descubra.data.repository

import com.ifsp.descubra.domain.model.Article

class FakeArticleRepository : ArticleRepository {

    override suspend fun getRandomArticle(): Article {
        return Article(
            title = "Android",
            description = "Android é o sistema operacional criado pelo Google para celulares, tablets e outros dispositivos."
        )
    }
}