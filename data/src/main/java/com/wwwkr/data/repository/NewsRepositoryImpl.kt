package com.wwwkr.data.repository

import com.wwwkr.data.datasource.remote.NewsRemoteSource
import com.wwwkr.data.mapper.mappingNewsParam
import com.wwwkr.data.mapper.mappingNewsResp
import com.wwwkr.domain.model.NewsParamData
import com.wwwkr.domain.model.NewsRespData
import com.wwwkr.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsRemoteSource: NewsRemoteSource) : NewsRepository{

    override fun getNews(param: NewsParamData): Flow<NewsRespData> = flow {
        newsRemoteSource.getNews(newsParam = param.mappingNewsParam()).collect {
            emit(it.mappingNewsResp())
        }
    }

}