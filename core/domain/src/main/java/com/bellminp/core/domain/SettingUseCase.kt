package com.bellminp.core.domain

import com.bellminp.core.data.model.asEntity
import com.bellminp.core.data.repository.SettingRepository
import com.bellminp.core.database.model.SettingEntity
import com.bellminp.core.database.model.asExternalModel
import com.bellminp.core.model.data.Setting
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingUseCase @Inject constructor(
    private val settingRepository: SettingRepository
) {
     fun getAllSettingList() = settingRepository.getAllSettingList()

     suspend fun insertSetting(type: Int) = settingRepository.insertSetting(Setting(id = 0, type = type, name = ""))

     suspend fun updateSetting(setting: Setting) = settingRepository.updateSetting(setting)

     suspend fun deleteSetting(id: Long) = settingRepository.deleteSetting(id)
}