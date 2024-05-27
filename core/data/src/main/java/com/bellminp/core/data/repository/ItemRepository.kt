package com.bellminp.core.data.repository

import com.bellminp.core.database.model.ItemEntity
import com.bellminp.core.model.data.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun getItemList() : Flow<List<Item>>
    suspend fun upsertItem(itemList : List<Item>)
    suspend fun deleteItem(id: Long)
}