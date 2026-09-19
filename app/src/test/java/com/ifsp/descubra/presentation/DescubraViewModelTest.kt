package com.ifsp.descubra.presentation

import com.ifsp.descubra.MainDispatcherRule
import com.ifsp.descubra.data.repository.ArticleRepository
import com.ifsp.descubra.domain.model.Article
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DescubraViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var articleRepository: ArticleRepository
    private lateinit var viewModel: DescubraViewModel

    @Before
    fun setup() {
        articleRepository = mockk()
        viewModel = DescubraViewModel(articleRepository)
    }

    @Test
    fun `should load article successfully`() = runTest {
        coEvery {
            articleRepository.getRandomArticle()
        } returns Article(
            title = "Android",
            description = "Sistema operacional móvel."
        )

        viewModel.discoverArticle()

        advanceUntilIdle()

        assertEquals(
            "Android",
            viewModel.articleTitle
        )

        assertEquals(
            "Sistema operacional móvel.",
            viewModel.articleDescription
        )

        assertFalse(viewModel.isLoading)
        assertNull(viewModel.errorMessage)
    }

    @Test
    fun `should show error when repository fails`() = runTest {
        coEvery {
            articleRepository.getRandomArticle()
        } throws Exception("API error")

        viewModel.discoverArticle()

        advanceUntilIdle()

        assertEquals(
            "Não foi possível descobrir algo novo. Tente novamente.",
            viewModel.errorMessage
        )

        assertFalse(viewModel.isLoading)
    }
}