package com.bellminp.core.database

import com.bellminp.core.database.dao.CategoryDao
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
}