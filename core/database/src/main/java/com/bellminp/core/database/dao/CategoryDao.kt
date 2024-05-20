package com.bellminp.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.bellminp.core.database.model.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query(value = "SELECT * FROM category ORDER BY displayOrder DESC")
    fun getCategoryList() : Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(categoryEntity: CategoryEntity) : Long

    @Upsert
    suspend fun upsertCategory(entities: List<CategoryEntity>)

    @Query(value = "DELETE FROM category WHERE id in (:ids)")
    suspend fun deleteCategory(ids: List<Long>)
}