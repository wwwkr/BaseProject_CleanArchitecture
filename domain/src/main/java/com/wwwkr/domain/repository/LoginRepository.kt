package com.wwwkr.domain.repository

import com.wwwkr.domain.model.LoginParamData
import com.wwwkr.domain.model.LoginRespData
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun requestLogin(param : LoginParamData) : Flow<LoginRespData>

}