package com.bellminp.core.data.repository

import com.bellminp.core.model.data.Brand
import com.bellminp.core.model.data.Category
import com.bellminp.core.model.data.Setting
import kotlinx.coroutines.flow.Flow

interface CaeRepository {
    fun getAllSettingList() : Flow<List<Setting>>
    fun getCategoryList() : Flow<List<Category>>
    fun getBrandList() : Flow<List<Brand>>
}