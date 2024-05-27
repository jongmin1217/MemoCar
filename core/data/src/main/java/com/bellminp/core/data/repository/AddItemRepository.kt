package com.bellminp.core.data.repository

import com.bellminp.core.model.data.Item
import kotlinx.coroutines.flow.Flow

interface AddItemRepository {
    fun getItemList() : Flow<List<Item>>
    suspend fun insertItem(item: Item) : Long
    suspend fun updateItem(item: Item)
}