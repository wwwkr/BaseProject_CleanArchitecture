package com.wwwkr.data.datasource.local

import com.wwwkr.data.datasource.local.database.AppDatabase
import com.wwwkr.data.datasource.local.database.entity.BaseEntity
import javax.inject.Inject

class BaseLocalSource @Inject constructor(private val appDatabase: AppDatabase) {

   suspend fun insertBaseData(items : List<BaseEntity>) = appDatabase.BaseDao().insert(items = items)

}