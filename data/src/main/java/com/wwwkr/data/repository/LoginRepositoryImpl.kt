package com.wwwkr.data.repository

import com.wwwkr.data.datasource.remote.LoginRemoteSource
import com.wwwkr.data.mapper.mappingLoginParam
import com.wwwkr.data.mapper.mappingLoginRespData
import com.wwwkr.data.model.request.LoginParam
import com.wwwkr.data.model.response.LoginResp
import com.wwwkr.domain.model.LoginParamData
import com.wwwkr.domain.model.LoginRespData
import com.wwwkr.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val loginRemoteSource: LoginRemoteSource) : LoginRepository{

    override fun requestLogin(param: LoginParamData): Flow<LoginRespData> = flow {
        loginRemoteSource.requestLogin(loginParam = param.mappingLoginParam()).collect {
            emit(it.mappingLoginRespData())
        }
    }

}