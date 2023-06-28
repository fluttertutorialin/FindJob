package com.alex.findjob.di

import com.alex.findjob.data.auth.AuthRepository
import com.alex.findjob.data.auth.AuthRepositoryImpl
import com.alex.findjob.data.repository.JobsRepository
import com.alex.findjob.data.repository.JobsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindJobsRepository(repositoryImpl: JobsRepositoryImpl): JobsRepository

    @Binds
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

}
