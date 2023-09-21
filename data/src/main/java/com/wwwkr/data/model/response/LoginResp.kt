package com.wwwkr.data.model.response

data class LoginResp(
    val result : String,
    val message : String,
    val data : List<LoginRespItem>
)

data class LoginRespItem(
    val token : String,
    val refresh_token : String,
    val role : String
)