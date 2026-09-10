package com.example.mamikostvapp.data

import com.example.mamikostvapp.data.repository.ShowRepository
import com.example.mamikostvapp.data.repository.ShowRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindShowRepository(
        repository: ShowRepository
    ): ShowRepositoryInterface
}