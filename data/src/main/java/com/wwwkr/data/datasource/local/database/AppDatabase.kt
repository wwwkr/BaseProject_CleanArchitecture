package com.wwwkr.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wwwkr.data.datasource.local.database.dao.BaseDao
import com.wwwkr.data.datasource.local.database.entity.BaseEntity

@Database(
    entities = [BaseEntity::class],
    version = 1,
    exportSchema = true,
    autoMigrations = [
        /**
         *  ex) AutoMigration (from = 1, to 2)
         */
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun BaseDao() : BaseDao
}