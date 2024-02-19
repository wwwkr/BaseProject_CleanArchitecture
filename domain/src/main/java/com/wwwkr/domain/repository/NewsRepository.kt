package com.wwwkr.domain.repository

import com.wwwkr.domain.model.ArticleData
import com.wwwkr.domain.model.NewsParamData
import com.wwwkr.domain.model.NewsRespData
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(param: NewsParamData): Flow<NewsRespData>

    fun insertNews(item: ArticleData): Flow<Unit>

    fun selectNews(): Flow<List<ArticleData>>

    fun deleteNews(title: String): Flow<Unit>
}