package com.bellminp.core.data.di

import com.bellminp.core.data.repository.BrandRepository
import com.bellminp.core.data.repository.BrandRepositoryImpl
import com.bellminp.core.data.repository.CaeRepository
import com.bellminp.core.data.repository.CarRepositoryImpl
import com.bellminp.core.data.repository.CategoryRepository
import com.bellminp.core.data.repository.CategoryRepositoryImpl
import com.bellminp.core.data.repository.DashboardRepository
import com.bellminp.core.data.repository.DashboardRepositoryImpl
import com.bellminp.core.data.repository.SettingRepository
import com.bellminp.core.data.repository.SettingRepositoryImpl
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

    @Binds
    fun bindsBrandRepository(
        brandRepository : BrandRepositoryImpl,
    ): BrandRepository

    @Binds
    fun bindsSettingRepository(
        settingRepository : SettingRepositoryImpl,
    ): SettingRepository

    @Binds
    fun bindsCarRepository(
        caeRepository : CarRepositoryImpl
    ): CaeRepository
}