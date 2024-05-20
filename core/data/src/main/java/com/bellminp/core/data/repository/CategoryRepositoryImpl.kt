package com.bellminp.core.data.repository

import com.bellminp.core.data.model.asEntity
import com.bellminp.core.database.dao.CategoryDao
import com.bellminp.core.database.model.CategoryEntity
import com.bellminp.core.database.model.asExternalModel
import com.bellminp.core.model.data.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryRepository{
    override fun getCategoryList(): Flow<List<Category>> =
        categoryDao.getCategoryList()
            .map { it.map(CategoryEntity::asExternalModel) }
    override suspend fun deleteCategory(ids: List<Long>) = categoryDao.deleteCategory(ids)

    override suspend fun upsertCategory(categoryList : List<Category>) = categoryDao.upsertCategory(categoryList.map(Category::asEntity))
    override suspend fun insertCategory(category: Category) = categoryDao.insertCategory(category.asEntity())
}