package org.sopt.and.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.dataremote.repository.UserServiceRepository
import org.sopt.and.data.dataremote.repositoryimpl.UserServiceRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindUserServiceRepository(userServiceRepositoryImpl: UserServiceRepositoryImpl):UserServiceRepository
}