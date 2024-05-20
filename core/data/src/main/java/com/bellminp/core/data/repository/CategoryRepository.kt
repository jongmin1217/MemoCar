package com.bellminp.core.data.repository

import com.bellminp.core.data.model.asEntity
import com.bellminp.core.database.model.CategoryEntity
import com.bellminp.core.model.data.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategoryList() : Flow<List<Category>>
    suspend fun deleteCategory(ids: List<Long>)
    suspend fun upsertCategory(categoryList : List<Category>)
    suspend fun insertCategory(category: Category) : Long

}