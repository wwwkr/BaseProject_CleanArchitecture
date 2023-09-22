package com.wwwkr.domain.model

import com.wwwkr.domain.BuildConfig


data class NewsParamData(
    val country : String,
    val apiKey : String = BuildConfig.API_KEY
)