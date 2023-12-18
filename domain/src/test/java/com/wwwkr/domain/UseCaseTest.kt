package com.wwwkr.domain

import com.wwwkr.domain.model.ArticleData
import com.wwwkr.domain.model.LoginParamData
import com.wwwkr.domain.model.NewsParamData
import com.wwwkr.domain.model.NewsRespData
import com.wwwkr.domain.repository.LoginRepository
import com.wwwkr.domain.repository.NewsRepository
import com.wwwkr.domain.usecase.LoginUseCase
import com.wwwkr.domain.usecase.NewsUseCase
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.core.test.TestCase
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK

import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import java.util.concurrent.Flow

class UseCaseTest : ShouldSpec({

    val newsRepository = mockk<NewsRepository>()
    val newsUseCase = NewsUseCase(newsRepository)


    val fakeNewsResponse = NewsRespData(
        articles = listOf<ArticleData>(),
        status = "00",
        totalResults = 0
    )

    every { newsUseCase.getNews(country = "kr") } returns flowOf(fakeNewsResponse)

    // 테스트 케이스
    context("API가 정상적으로 호출 했을 때") {

        // Flow를 collect하여 값을 처리
        val result = mutableListOf<NewsRespData>()
        newsUseCase.getNews(country = "kr").collect {
            result.add(it)
        }

        should("status 는 00을 반환 한다") {
            // 마지막으로 방출된 값의 status가 "00"인지 확인
            result.lastOrNull()?.status shouldBe "00"
        }
    }
})

