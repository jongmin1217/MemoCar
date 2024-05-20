package com.bellminp.core.data.repository

import com.bellminp.core.model.data.Category
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {
    fun getCategoryList() : Flow<List<Category>>
}