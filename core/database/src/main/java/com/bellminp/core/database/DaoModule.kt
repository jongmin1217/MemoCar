package com.bellminp.core.database

import com.bellminp.core.database.dao.BrandDao
import com.bellminp.core.database.dao.CategoryDao
import com.bellminp.core.database.dao.SettingDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun providesCategoryDao(
        database: MemoCarDatabase
    ) : CategoryDao = database.categoryDao()

    @Provides
    fun providesBrandDao(
        database: MemoCarDatabase
    ) : BrandDao = database.brandDao()

    @Provides
    fun providesSettingDao(
        database: MemoCarDatabase
    ) : SettingDao = database.settingDao()
}