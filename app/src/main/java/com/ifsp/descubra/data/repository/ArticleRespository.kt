package com.ifsp.descubra.data.repository

import com.ifsp.descubra.domain.model.Article

interface ArticleRepository {

    suspend fun getRandomArticle(): Article
}