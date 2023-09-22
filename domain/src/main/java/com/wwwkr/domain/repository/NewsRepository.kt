package com.wwwkr.domain.repository

import com.wwwkr.domain.model.NewsParamData
import com.wwwkr.domain.model.NewsRespData
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(param: NewsParamData) : Flow<NewsRespData>

}