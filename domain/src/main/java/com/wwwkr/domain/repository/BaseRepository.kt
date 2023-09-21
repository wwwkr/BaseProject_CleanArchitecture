package com.wwwkr.domain.repository

import com.wwwkr.domain.model.Base

interface BaseRepository {

    suspend fun insertBaseData(items: List<Base>)

}