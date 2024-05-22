package com.bellminp.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.bellminp.core.database.model.BrandEntity
import com.bellminp.core.database.model.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BrandDao {
    @Query(value = "SELECT * FROM brand ORDER BY name ASC")
    fun getBrandList() : Flow<List<BrandEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBrand(brandEntity: BrandEntity) : Long

    @Update
    suspend fun updateBrand(brandEntity: BrandEntity)

    @Query(value = "DELETE FROM brand WHERE id in (:id)")
    suspend fun deleteBrand(id: Long)
}