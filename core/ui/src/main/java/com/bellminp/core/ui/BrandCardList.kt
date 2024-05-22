package com.bellminp.core.ui

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import com.bellminp.core.model.data.Brand
import com.bellminp.core.model.data.Category
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.ReorderableLazyListState


fun LazyListScope.brandCardList(
    items : List<Brand>,
    isKeyboardOpen : Boolean,
    itemModifier: Modifier = Modifier,
    onDeleteClick : (Brand) -> Unit,
    onNameChange : (Brand) -> Unit
) = items(
    items = items,
    key = {it.id},
    itemContent = {item: Brand ->
        BrandCard(
            brand = item,
            isKeyboardOpen = isKeyboardOpen,
            modifier = itemModifier,
            onDeleteClick = onDeleteClick,
            onNameChange = onNameChange
        )
    }
)