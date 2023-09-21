package com.wwwkr.data.datasource.remote

import com.wwwkr.data.model.request.LoginParam
import com.wwwkr.data.model.request.RefreshTokenParam
import com.wwwkr.data.model.response.LoginResp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @POST("user/auth")
    suspend fun requestLogin(@Body loginParam: LoginParam) : LoginResp

    @POST("user/accessToken_refresh")
    fun requestRefreshToken(@Header("refresh_token") refreshToken: String) : Call<LoginResp>

}