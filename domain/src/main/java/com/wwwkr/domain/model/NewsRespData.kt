package com.wwwkr.domain.model

data class NewsRespData(
    val articles: List<ArticleData>?,
    val status: String?,
    val totalResults: Int?
)

data class ArticleData(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: SourceData?,
    val title: String?,
    val url: String?,
    val urlToImage: String?,
    var isScraped: Boolean
)

data class SourceData(
//    val id: Int?,
    val name: String?
)

