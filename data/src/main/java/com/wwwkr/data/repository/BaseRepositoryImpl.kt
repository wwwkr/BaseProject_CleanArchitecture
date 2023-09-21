package com.wwwkr.data.repository

import com.wwwkr.data.datasource.local.BaseLocalSource
import com.wwwkr.data.mapper.mappingBaseEntity
import com.wwwkr.domain.model.Base
import com.wwwkr.domain.repository.BaseRepository
import javax.inject.Inject

class BaseRepositoryImpl @Inject constructor(private val baseLocalSource: BaseLocalSource) : BaseRepository {

    override suspend fun insertBaseData(items: List<Base>) {
        baseLocalSource.insertBaseData(
            items = items.map {
                it.mappingBaseEntity()
            })
    }
}