package com.wwwkr.data.repository

import com.wwwkr.data.datasource.local.NewsLocalSource
import com.wwwkr.data.datasource.remote.NewsRemoteSource
import com.wwwkr.data.mapper.mappingArticleDataList
import com.wwwkr.data.mapper.mappingNewsEntity
import com.wwwkr.data.mapper.mappingNewsParam
import com.wwwkr.data.mapper.mappingNewsResp
import com.wwwkr.domain.model.ArticleData
import com.wwwkr.domain.model.NewsParamData
import com.wwwkr.domain.model.NewsRespData
import com.wwwkr.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsRemoteSource: NewsRemoteSource,
    private val newsLocalSource: NewsLocalSource
) : NewsRepository {

    override fun getNews(param: NewsParamData): Flow<NewsRespData> = flow {
        newsRemoteSource.getNews(newsParam = param.mappingNewsParam()).collect {
            emit(it.mappingNewsResp())
        }
    }

    override fun insertNews(item: ArticleData): Flow<Unit> = flow {
        newsLocalSource.insertNews(item = item.mappingNewsEntity()).collect {
            emit(it)
        }
    }

    override fun selectNews(): Flow<List<ArticleData>> = flow {
        newsLocalSource.selectNews().collect {
            emit(it.mappingArticleDataList())
        }
    }

    override fun deleteNews(title: String): Flow<Unit> = flow {
        newsLocalSource.deleteNews(title = title).collect() {
            emit(it)
        }
    }

}