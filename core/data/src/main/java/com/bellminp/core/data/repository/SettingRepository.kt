package com.bellminp.core.data.repository

import com.bellminp.core.database.model.SettingEntity
import com.bellminp.core.model.data.Setting
import kotlinx.coroutines.flow.Flow

interface SettingRepository {
    fun getSettingList(id : Long) : Flow<List<Setting>>
    suspend fun insertSetting(setting: Setting) : Long
    suspend fun updateSetting(setting: Setting)
    suspend fun deleteSetting(id: Long)
}