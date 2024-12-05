package org.sopt.and.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.repositoryimpl.DataStoreRepositoryImpl
import org.sopt.and.data.repositoryimpl.UserServiceRepositoryImpl
import org.sopt.and.domain.repository.DataStoreRepository
import org.sopt.and.domain.repository.UserServiceRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindUserServiceRepository(userServiceRepositoryImpl: UserServiceRepositoryImpl): UserServiceRepository

    @Binds
    @Singleton
    abstract fun bindDataStoreRepository(dataStoreRepositoryImpl: DataStoreRepositoryImpl): DataStoreRepository
}