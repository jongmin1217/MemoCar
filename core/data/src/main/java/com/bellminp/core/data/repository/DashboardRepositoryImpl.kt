package com.bellminp.core.data.repository

import com.bellminp.core.database.dao.SettingDao
import com.bellminp.core.database.model.SettingEntity
import com.bellminp.core.database.model.asExternalModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
    private val settingDao: SettingDao
) : DashboardRepository {
    override fun getPartList(type: Int) = settingDao.getPartList(type).map { it.map(SettingEntity::asExternalModel) }
}