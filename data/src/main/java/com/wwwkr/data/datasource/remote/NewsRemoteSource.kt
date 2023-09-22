package com.wwwkr.data.datasource.remote

import com.wwwkr.data.model.request.NewsParam
import com.wwwkr.data.model.response.NewsResp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRemoteSource @Inject constructor(private val apiService: ApiService) {

    fun getNews(newsParam: NewsParam) : Flow<NewsResp> = flow {
        emit(apiService.getNews(country = newsParam.country, apiKey = newsParam.apiKey))
    }
}