package com.wwwkr.domain.usecase

import com.wwwkr.domain.model.LoginParamData
import com.wwwkr.domain.model.LoginRespData
import com.wwwkr.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    fun requestLogin(user_id: String, user_pw: Int): Flow<LoginRespData> {
        return loginRepository.requestLogin(
            LoginParamData(
                user_id = user_id,
                user_pw = user_pw
            )
        )
    }

}