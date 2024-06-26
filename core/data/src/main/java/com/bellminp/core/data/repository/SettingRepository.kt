package com.bellminp.core.data.repository

import com.bellminp.core.model.data.Setting
import kotlinx.coroutines.flow.Flow

interface SettingRepository {
    fun getAllSettingList() : Flow<List<Setting>>
    fun getPartList(type : Int) : Flow<List<Setting>>
    suspend fun insertSetting(setting: Setting) : Long
    suspend fun updateSetting(setting: Setting)
    suspend fun deleteSetting(id: Long)
    suspend fun upsertSetting(settingList : List<Setting>)

}