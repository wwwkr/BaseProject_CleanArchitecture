package com.wwwkr.data.datasource.remote

import com.wwwkr.data.model.request.LoginParam
import com.wwwkr.data.model.response.LoginResp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRemoteSource @Inject constructor(private val authService : AuthService) {

    fun requestLogin(loginParam: LoginParam) : Flow<LoginResp> = flow {
        emit(authService.requestLogin(loginParam = loginParam))
    }

}