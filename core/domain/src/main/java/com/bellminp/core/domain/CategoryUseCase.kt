package com.bellminp.core.domain

import com.bellminp.core.data.model.asEntity
import com.bellminp.core.data.repository.CategoryRepository
import com.bellminp.core.database.model.CategoryEntity
import com.bellminp.core.database.model.asExternalModel
import com.bellminp.core.model.data.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    fun getCategoryList(): Flow<List<Category>> = categoryRepository.getCategoryList()

    suspend fun deleteCategory(ids: List<Long>) = categoryRepository.deleteCategory(ids)

    suspend fun upsertCategory(categoryList : List<Category>) = categoryRepository.upsertCategory(categoryList)

    suspend fun insertCategory(categoryText : String){
        val listSize = getCategoryList().first().size
        categoryRepository.insertCategory(
            Category(
                id = 0,
                name = categoryText,
                displayOrder = listSize
            )
        )
    }
}