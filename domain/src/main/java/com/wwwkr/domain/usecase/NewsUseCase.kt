package com.wwwkr.domain.usecase

import android.util.Log
import com.wwwkr.domain.model.ArticleData
import com.wwwkr.domain.model.LoginParamData
import com.wwwkr.domain.model.LoginRespData
import com.wwwkr.domain.model.NewsParamData
import com.wwwkr.domain.model.NewsRespData
import com.wwwkr.domain.repository.LoginRepository
import com.wwwkr.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    fun getNews(country : String): Flow<NewsRespData> {
        return newsRepository.getNews(
            NewsParamData(
                country = country
            )
        )
    }

    fun insertNews(item: ArticleData): Flow<Unit> {
        return newsRepository.insertNews(item = item)
    }

    fun selectNews(): Flow<List<ArticleData>> {
        return newsRepository.selectNews()
    }

    fun deleteNews(title: String): Flow<Unit> = newsRepository.deleteNews(title = title)
}