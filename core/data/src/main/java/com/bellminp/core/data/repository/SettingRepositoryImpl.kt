package com.bellminp.core.data.repository

import com.bellminp.core.data.model.asEntity
import com.bellminp.core.database.dao.SettingDao
import com.bellminp.core.database.model.CategoryEntity
import com.bellminp.core.database.model.SettingEntity
import com.bellminp.core.database.model.asExternalModel
import com.bellminp.core.model.data.Setting
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor(
    private val settingDao: SettingDao
) : SettingRepository{
    override fun getSettingList(id : Long) = settingDao.getSettingList(id).map { it.map(SettingEntity::asExternalModel) }

    override suspend fun insertSetting(setting: Setting) = settingDao.insertSetting(setting.asEntity())

    override suspend fun updateSetting(setting: Setting) = settingDao.updateSetting(setting.asEntity())

    override suspend fun deleteSetting(id: Long) = settingDao.deleteSetting(id)
}