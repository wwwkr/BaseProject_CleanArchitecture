package com.wwwkr.data.mapper

import com.wwwkr.data.datasource.local.database.entity.BaseEntity
import com.wwwkr.data.model.request.LoginParam
import com.wwwkr.data.model.response.LoginResp
import com.wwwkr.domain.model.Base
import com.wwwkr.domain.model.LoginParamData
import com.wwwkr.domain.model.LoginRespData
import com.wwwkr.domain.model.LoginRespItemData

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
