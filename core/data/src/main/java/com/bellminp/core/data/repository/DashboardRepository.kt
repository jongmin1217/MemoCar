package com.bellminp.core.data.repository

import com.bellminp.core.model.data.Setting
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {
    fun getPartList(type : Int) : Flow<List<Setting>>
}