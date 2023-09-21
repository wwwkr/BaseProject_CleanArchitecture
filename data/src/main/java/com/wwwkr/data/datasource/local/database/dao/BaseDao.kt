package com.wwwkr.data.datasource.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.wwwkr.data.datasource.local.database.entity.BaseEntity

@Dao
interface BaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<BaseEntity>)

}