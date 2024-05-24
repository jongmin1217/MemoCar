package com.bellminp.core.data.repository

import com.bellminp.core.database.dao.SettingDao
import com.bellminp.core.database.model.SettingEntity
import com.bellminp.core.database.model.asExternalModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(
    private val settingDao: SettingDao
) : CarRepository{
    override fun getAllSettingList() = settingDao.getAllSettingList().map { it.map(SettingEntity::asExternalModel) }
}