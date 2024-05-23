package com.bellminp.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bellminp.core.database.model.BrandEntity
import com.bellminp.core.database.model.SettingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingDao {
    @Query(value = "SELECT * FROM setting WHERE type = :id ORDER BY name ASC")
    fun getSettingList(id : Long) : Flow<List<SettingEntity>>

    @Query(value = "SELECT * FROM setting ORDER BY name ASC")
    fun getAllSettingList() : Flow<List<SettingEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSetting(settingEntity: SettingEntity) : Long

    @Update
    suspend fun updateSetting(settingEntity: SettingEntity)

    @Query(value = "DELETE FROM setting WHERE id in (:id)")
    suspend fun deleteSetting(id: Long)
}