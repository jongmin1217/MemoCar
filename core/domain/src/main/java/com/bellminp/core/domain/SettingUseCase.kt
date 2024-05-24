package com.bellminp.core.domain

import com.bellminp.core.data.repository.SettingRepository
import com.bellminp.core.model.data.SelectStateType
import com.bellminp.core.model.data.Setting
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class SettingUseCase @Inject constructor(
    private val settingRepository: SettingRepository,
) {
    fun getAllSettingList() = settingRepository.getAllSettingList()

    fun getPartList(type : Int) = settingRepository.getPartList(type)

    suspend fun insertSetting(type: Int) = settingRepository.insertSetting(
        Setting(
            id = 0,
            type = type,
            name = "",
            displayOrder = if (type == SelectStateType.CATEGORY.id) {
                val maxOrder = getPartList(SelectStateType.CATEGORY.id).first().maxOfOrNull { it.displayOrder }
                maxOrder?.plus(1)?:0
            } else 0,
            isShowImage = type == SelectStateType.BRAND.id,
            imageUrl = null
        )
    )

    suspend fun upsertSetting(settingList: List<Setting>) = settingRepository.upsertSetting(
        settingList.mapIndexed { index, category -> category.copy(displayOrder = index)}
    )

    suspend fun updateSetting(setting: Setting) = settingRepository.updateSetting(setting)

    suspend fun deleteSetting(id: Long) = settingRepository.deleteSetting(id)
}