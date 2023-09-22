package com.wwwkr.data.datasource.remote

import com.wwwkr.data.model.request.LoginParam
import com.wwwkr.data.model.response.LoginResp
import com.wwwkr.data.model.response.NewsResp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("user/auth")
    suspend fun requestLogin(@Body loginParam: LoginParam) : LoginResp

    @POST("user/accessToken_refresh")
    fun requestRefreshToken(@Header("refresh_token") refreshToken: String) : Call<LoginResp>

    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country : String,
        @Query("apiKey") apiKey : String
    ) : NewsResp

}