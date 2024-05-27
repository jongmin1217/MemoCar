package com.bellminp.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.bellminp.core.database.model.ItemEntity
import com.bellminp.core.database.model.SettingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query(value = "SELECT * FROM item ORDER BY displayOrder ASC")
    fun getItemList() : Flow<List<ItemEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(itemEntity: ItemEntity) : Long

    @Update
    suspend fun updateItem(itemEntity: ItemEntity)

    @Upsert
    suspend fun upsertItem(itemList : List<ItemEntity>)

    @Query(value = "DELETE FROM item WHERE id in (:id)")
    suspend fun deleteItem(id: Long)

}