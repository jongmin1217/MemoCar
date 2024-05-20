package com.bellminp.core.data.repository

import com.bellminp.core.database.dao.CategoryDao
import com.bellminp.core.database.model.CategoryEntity
import com.bellminp.core.database.model.asExternalModel
import com.bellminp.core.model.data.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
) : DashboardRepository {
    override fun getCategoryList(): Flow<List<Category>> =
        categoryDao.getCategoryList()
            .map { it.map(CategoryEntity::asExternalModel) }
}