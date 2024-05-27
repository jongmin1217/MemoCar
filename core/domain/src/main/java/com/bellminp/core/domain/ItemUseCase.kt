package com.bellminp.core.domain

import com.bellminp.core.data.model.asEntity
import com.bellminp.core.data.repository.AddItemRepository
import com.bellminp.core.data.repository.ItemRepository
import com.bellminp.core.database.model.ItemEntity
import com.bellminp.core.database.model.asExternalModel
import com.bellminp.core.model.data.Item
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ItemUseCase @Inject constructor(
    private val itemRepository: ItemRepository
) {
    fun getItemList() = itemRepository.getItemList()

    suspend fun upsertItem(itemList: List<Item>) = itemRepository.upsertItem(itemList)

    suspend fun deleteItem(id: Long) = itemRepository.deleteItem(id)
}