package com.github.mhelmi.thmanyahtask.di

import com.github.mhelmi.thmanyahtask.data.HomeRepositoryImpl
import com.github.mhelmi.thmanyahtask.domain.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
  @Binds
  abstract fun provideHomeRepository(
    homeRepository: HomeRepositoryImpl
  ): HomeRepository
}