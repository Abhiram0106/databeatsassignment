package com.abhiram.databeatassessment

import com.abhiram.databeatassessment.feature_home.data.model.ArticleSourceDto
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import com.abhiram.databeatassessment.feature_home.data.model.ArticlesDto
import com.abhiram.databeatassessment.feature_home.data.model.GetNewsResponseDto
import com.abhiram.databeatassessment.feature_home.domain.toDomain
import com.abhiram.databeatassessment.feature_home.domain.toGetNewsResponse
import kotlin.test.assertNotNull

class MappersTest {

    @Test
    fun `toDomain should correctly map ArticlesDto to NewsItem`() {
        val articlesDto = ArticlesDto(
            source = ArticleSourceDto(name = "Test Source"),
            urlToImage = "https://example.com/image.jpg",
            title = "Test Title",
            description = "Test Description",
            publishedAt = "2024-03-30T12:00:00Z",
            url = "https://example.com/article"
        )

        val newsItem = articlesDto.toDomain()

        assertEquals("Test Source", newsItem.sourceName)
        assertEquals("https://example.com/image.jpg", newsItem.imageUrl)
        assertEquals("Test Title", newsItem.title)
        assertEquals("Test Description", newsItem.description)
        assertEquals("https://example.com/article", newsItem.articleUrl)
        assertNotNull(newsItem.publishedAt)
    }

    @Test
    fun `toDomain should correctly map a list of ArticlesDto to a list of NewsItem`() {
        val articlesDtoList = listOf(
            ArticlesDto(
                ArticleSourceDto(name = "Source1"),
                "url1",
                "Title1",
                "Desc1",
                "2024-03-30T12:00:00Z",
                "articleUrl1"
            ),
            ArticlesDto(
                ArticleSourceDto(name = "Source2"),
                "url2",
                "Title2",
                "Desc2",
                "2024-03-31T14:00:00Z",
                "articleUrl2"
            )
        )

        val newsItemList = articlesDtoList.toDomain()

        assertEquals(2, newsItemList.size)
        assertEquals("Source1", newsItemList[0].sourceName)
        assertEquals("Source2", newsItemList[1].sourceName)
    }

    @Test
    fun `toDomain should correctly map GetNewsResponseDto to GetNewsResponse`() {
        val getNewsResponseDto = GetNewsResponseDto(
            articles = listOf(
                ArticlesDto(
                    ArticleSourceDto(name = "Source1"),
                    "url1",
                    "Title1",
                    "Desc1",
                    "2024-03-30T12:00:00Z",
                    "articleUrl1"
                )
            )
        )

        val getNewsResponse = getNewsResponseDto.toDomain()

        assertEquals(1, getNewsResponse.articles.size)
        assertEquals("Source1", getNewsResponse.articles[0].sourceName)
    }

    @Test
    fun `toGetNewsResponse should correctly map Result GetNewsResponseDto to Result GetNewsResponse`() {
        val successResult: Result<GetNewsResponseDto> = Result.success(
            GetNewsResponseDto(
                articles = listOf(
                    ArticlesDto(
                        ArticleSourceDto(name = "Source1"),
                        "url1",
                        "Title1",
                        "Desc1",
                        "2024-03-30T12:00:00Z",
                        "articleUrl1"
                    )
                )
            )
        )

        val mappedResult = successResult.toGetNewsResponse()

        assertTrue(mappedResult.isSuccess)
        assertEquals("Source1", mappedResult.getOrThrow().articles[0].sourceName)

        val failureResult: Result<GetNewsResponseDto> = Result.failure(Exception("Test Error"))
        val mappedFailure = failureResult.toGetNewsResponse()

        assertTrue(mappedFailure.isFailure)
    }
}