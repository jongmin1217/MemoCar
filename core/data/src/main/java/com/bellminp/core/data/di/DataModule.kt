package com.bellminp.core.data.di

import com.bellminp.core.data.repository.CarRepository
import com.bellminp.core.data.repository.CarRepositoryImpl
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
    fun bindsSettingRepository(
        settingRepository : SettingRepositoryImpl,
    ): SettingRepository

    @Binds
    fun bindsCarRepository(
        carRepository : CarRepositoryImpl
    ): CarRepository

    @Binds
    fun bindsDashBoardRepository(
        dashboardRepository : DashboardRepositoryImpl
    ): DashboardRepository
}