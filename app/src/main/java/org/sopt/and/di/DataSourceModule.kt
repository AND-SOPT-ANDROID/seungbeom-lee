package org.sopt.and.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.dataremote.datasouceimpl.UserServiceRemoteDataSourceImpl
import org.sopt.and.data.dataremote.datasource.UserServiceRemoteDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindUserServiceRemoteDataSource(userServiceRemoteDataSourceImpl: UserServiceRemoteDataSourceImpl): UserServiceRemoteDataSource
}