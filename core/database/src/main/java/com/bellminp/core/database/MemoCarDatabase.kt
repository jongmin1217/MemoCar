package com.bellminp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bellminp.core.database.dao.BrandDao
import com.bellminp.core.database.dao.CategoryDao
import com.bellminp.core.database.dao.SettingDao
import com.bellminp.core.database.model.BrandEntity
import com.bellminp.core.database.model.CategoryEntity
import com.bellminp.core.database.model.SettingEntity
import com.bellminp.core.database.util.ListConverter

@Database(entities = [CategoryEntity::class, BrandEntity::class, SettingEntity::class], version = 1, exportSchema = true)
@TypeConverters(ListConverter::class,)
abstract class MemoCarDatabase : RoomDatabase(){
    abstract fun categoryDao() : CategoryDao
    abstract fun brandDao() : BrandDao
    abstract fun settingDao() : SettingDao
}