package com.bellminp.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.bellminp.core.database.model.SettingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingDao {
    @Query(value = "SELECT * FROM setting WHERE type = :type ORDER BY name ASC")
    fun getPartList(type : Int) : Flow<List<SettingEntity>>

    @Query(value = "SELECT * FROM setting ORDER BY name ASC")
    fun getAllSettingList() : Flow<List<SettingEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSetting(settingEntity: SettingEntity) : Long

    @Update
    suspend fun updateSetting(settingEntity: SettingEntity)

    @Upsert
    suspend fun upsertSetting(settingList : List<SettingEntity>)

    @Query(value = "DELETE FROM setting WHERE id in (:id)")
    suspend fun deleteSetting(id: Long)
}