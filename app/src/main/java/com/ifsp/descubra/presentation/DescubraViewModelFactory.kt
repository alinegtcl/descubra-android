package com.ifsp.descubra.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ifsp.descubra.data.repository.ArticleRepository

class DescubraViewModelFactory(
    private val articleRepository: ArticleRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DescubraViewModel::class.java)) {
            return DescubraViewModel(articleRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
