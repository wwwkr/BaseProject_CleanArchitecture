package com.wwwkr.domain.model

data class LoginRespData(
    val result : String,
    val message : String,
    val data : List<LoginRespItemData>
)

data class LoginRespItemData(
    val token : String,
    val refresh_token : String,
    val role : String
)
