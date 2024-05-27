package com.bellminp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bellminp.core.database.dao.ItemDao
import com.bellminp.core.database.dao.SettingDao
import com.bellminp.core.database.model.ItemEntity
import com.bellminp.core.database.model.SettingEntity
import com.bellminp.core.database.util.InputTypeConverter
import com.bellminp.core.database.util.ListConverter

@Database(entities = [SettingEntity::class, ItemEntity::class], version = 1, exportSchema = true)
@TypeConverters(ListConverter::class, InputTypeConverter::class)
abstract class MemoCarDatabase : RoomDatabase(){
    abstract fun settingDao() : SettingDao
    abstract fun itemDao() : ItemDao
}