package com.bellminp.core.database

import com.bellminp.core.database.dao.ItemDao
import com.bellminp.core.database.dao.SettingDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun providesSettingDao(
        database: MemoCarDatabase
    ) : SettingDao = database.settingDao()

    @Provides
    fun providesItemDao(
        database: MemoCarDatabase
    ) : ItemDao = database.itemDao()
}