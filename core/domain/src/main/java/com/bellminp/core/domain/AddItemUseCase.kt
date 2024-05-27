package com.bellminp.core.domain

import com.bellminp.core.data.model.asEntity
import com.bellminp.core.data.repository.AddItemRepository
import com.bellminp.core.model.data.Item
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AddItemUseCase @Inject constructor(
    private val addItemRepository: AddItemRepository
) {

    suspend fun insertItem(item: Item) = addItemRepository.insertItem(item.copy(displayOrder = (addItemRepository.getItemList().first().maxOfOrNull { it.displayOrder }?:0) + 1))

    suspend fun updateItem(item: Item) = addItemRepository.updateItem(item)

}