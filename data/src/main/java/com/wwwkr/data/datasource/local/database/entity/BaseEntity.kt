package com.wwwkr.data.datasource.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "base")
data class BaseEntity(
    @PrimaryKey(autoGenerate = true)
    var uid : Int = 0,
    var test : String,
)