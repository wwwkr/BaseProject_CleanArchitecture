package com.wwwkr.data.mapper

import com.wwwkr.data.datasource.local.database.entity.BaseEntity
import com.wwwkr.data.datasource.local.database.entity.NewsEntity
import com.wwwkr.data.model.request.LoginParam
import com.wwwkr.data.model.request.NewsParam
import com.wwwkr.data.model.response.Article
import com.wwwkr.data.model.response.LoginResp
import com.wwwkr.data.model.response.NewsResp
import com.wwwkr.data.model.response.Source
import com.wwwkr.domain.model.ArticleData
import com.wwwkr.domain.model.Base
import com.wwwkr.domain.model.LoginParamData
import com.wwwkr.domain.model.LoginRespData
import com.wwwkr.domain.model.LoginRespItemData
import com.wwwkr.domain.model.NewsParamData
import com.wwwkr.domain.model.NewsRespData
import com.wwwkr.domain.model.SourceData

fun Base.mappingBaseEntity(): BaseEntity = BaseEntity(test = this.test)

fun LoginParamData.mappingLoginParam(): LoginParam =
    LoginParam(
        user_id = user_id,
        user_pw = user_pw
    )

fun LoginResp.mappingLoginRespData(): LoginRespData {

    return LoginRespData(
        result = result,
        message = message,
        data = listOf(
            LoginRespItemData(
                token = data.first().token,
                refresh_token = data.first().refresh_token,
                role = data.first().role
            )
        )
    )
}

fun NewsParamData.mappingNewsParam(): NewsParam =
    NewsParam(
        country = country,
        apiKey = apiKey
    )

fun NewsResp.mappingNewsResp(): NewsRespData =
    NewsRespData(
        articles = articles?.map { ArticleData(
            author = it.author,
            content = it.content,
            description = it.description,
            publishedAt = it.publishedAt,
            source = SourceData(
//                id = it.source?.id,
                name = it.source?.name
            ),
            title = it.title,
            url = it.url,
            urlToImage = it.urlToImage,
            isScraped = false
        ) },
        status = status,
        totalResults = totalResults
    )

fun ArticleData.mappingNewsEntity(): NewsEntity =
    NewsEntity(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        title = title,
        url = url,
        urlToImage = urlToImage,
        isScraped = isScraped
    )

fun List<NewsEntity>.mappingArticleDataList(): List<ArticleData> =
    map {
        ArticleData(
            author = it.author,
            content = it.content,
            description = it.description,
            publishedAt = it.publishedAt,
            source = null,
            title = it.title,
            url = it.url,
            urlToImage = it.urlToImage,
            isScraped = it.isScraped
        )
    }


