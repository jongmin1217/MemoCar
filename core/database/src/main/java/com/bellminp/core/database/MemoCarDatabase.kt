package com.bellminp.core.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bellminp.core.database.dao.CategoryDao
import com.bellminp.core.database.model.CategoryEntity
import com.bellminp.core.database.util.InstantConverter

@Database(entities = [CategoryEntity::class], version = 1, exportSchema = true)
@TypeConverters(InstantConverter::class,)
abstract class MemoCarDatabase : RoomDatabase(){
    abstract fun categoryDao() : CategoryDao
}