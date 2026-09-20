package com.ifsp.descubra.presentation

import com.ifsp.descubra.data.repository.ArticleRepository
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class DescubraViewModelFactoryTest {

    private val articleRepository = mockk<ArticleRepository>()

    private val factory = DescubraViewModelFactory(
        articleRepository = articleRepository
    )

    @Test
    fun `should create DescubraViewModel instance`() {
        val viewModel = factory.create(DescubraViewModel::class.java)

        assertNotNull(viewModel)
        assertTrue(viewModel is DescubraViewModel)
    }

}