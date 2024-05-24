package com.bellminp.core.data.repository

import com.bellminp.core.model.data.Setting
import kotlinx.coroutines.flow.Flow

interface CarRepository {
    fun getAllSettingList() : Flow<List<Setting>>
}