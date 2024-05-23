package com.bellminp.core.domain

import android.util.Log
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

    suspend fun deleteCategory(id : Long) = categoryRepository.deleteCategory(id)

    suspend fun upsertCategory(categoryList : List<Category>){
        categoryRepository.upsertCategory(categoryList.mapIndexed { index, category -> category.copy(displayOrder = index)})
    }

    suspend fun insertCategory(){
        val maxOrder = getCategoryList().first().maxOfOrNull { it.displayOrder }
        categoryRepository.insertCategory(
            Category(
                id = 0,
                name = "",
                displayOrder = maxOrder?.plus(1)?:0
            )
        )
    }

    suspend fun updateCategory(category : Category) = categoryRepository.updateCategory(category)
}