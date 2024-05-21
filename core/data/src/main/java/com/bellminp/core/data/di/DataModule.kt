package com.bellminp.core.data.di

import com.bellminp.core.data.repository.CategoryRepository
import com.bellminp.core.data.repository.CategoryRepositoryImpl
import com.bellminp.core.data.repository.DashboardRepository
import com.bellminp.core.data.repository.DashboardRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsCategoryRepository(
        categoryRepository: CategoryRepositoryImpl,
    ): CategoryRepository

    @Binds
    fun bindsDashBoardRepository(
        dashBoardRepository: DashboardRepositoryImpl,
    ): DashboardRepository
}