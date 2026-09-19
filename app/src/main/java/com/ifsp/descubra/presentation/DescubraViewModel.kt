package com.ifsp.descubra.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ifsp.descubra.data.repository.ArticleRepository
import kotlinx.coroutines.launch

class DescubraViewModel(
    private val articleRepository: ArticleRepository
) : ViewModel() {

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
                val response = articleRepository.getRandomArticle()

                articleTitle = response.title
                articleDescription = response.description

            } catch (exception: Exception) {
                errorMessage = "Não foi possível descobrir algo novo. Tente novamente."
            } finally {
                isLoading = false
            }
        }
    }
}