package com.wwwkr.baseproject_cleanarchitecture.di

import com.wwwkr.data.datasource.local.BaseLocalSource
import com.wwwkr.data.datasource.remote.LoginRemoteSource
import com.wwwkr.data.repository.BaseRepositoryImpl
import com.wwwkr.data.repository.LoginRepositoryImpl
import com.wwwkr.domain.repository.BaseRepository
import com.wwwkr.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun baseRepository(
        baseLocalSource: BaseLocalSource
    ): BaseRepository = BaseRepositoryImpl(baseLocalSource = baseLocalSource)

    @Provides
    @Singleton
    fun loginRepository(
        loginRemoteSource: LoginRemoteSource
    ): LoginRepository = LoginRepositoryImpl(loginRemoteSource = loginRemoteSource)

}