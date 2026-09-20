package com.ifsp.descubra.data.repository

import com.ifsp.descubra.data.remote.WikipediaResponse
import com.ifsp.descubra.data.remote.WikipediaService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class ArticleRepositoryImplTest {

    private val wikipediaService = mockk<WikipediaService>()

    private val repository = ArticleRepositoryImpl(
        wikipediaService = wikipediaService
    )

    @Test
    fun `should map wikipedia response to article`() = runTest {

        coEvery {
            wikipediaService.getRandomArticle()
        } returns WikipediaResponse(
            title = "Android",
            extract = "Sistema operacional móvel."
        )

        val result = repository.getRandomArticle()

        coVerify(exactly = 1) {
            wikipediaService.getRandomArticle()
        }

        assertEquals("Android", result.title)
        assertEquals("Sistema operacional móvel.", result.description)
    }

    @Test
    fun `should propagate service exception`() = runTest {

        coEvery {
            wikipediaService.getRandomArticle()
        } throws Exception("API error")

        assertThrows(Exception::class.java) {
            runBlocking {
                repository.getRandomArticle()
            }
        }
    }

}