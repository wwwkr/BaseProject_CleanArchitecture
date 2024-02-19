package com.wwwkr.baseproject_cleanarchitecture.di

import android.content.Context
import androidx.room.Room
import com.wwwkr.data.datasource.local.database.AppDatabase
import com.wwwkr.data.datasource.local.database.dao.BaseDao
import com.wwwkr.data.datasource.local.database.dao.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "baseProject.db"
        ).build()
    }

    @Provides
    fun provideMainDao(appDatabase: AppDatabase): BaseDao {
        return appDatabase.BaseDao()
    }

    @Provides
    fun provideNewsDao(appDatabase: AppDatabase): NewsDao {
        return appDatabase.NewsDao()
    }
}