package com.bellminp.core.ui

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import com.bellminp.core.model.data.Brand
import com.bellminp.core.model.data.SwipeItem


fun LazyListScope.swipeCardList(
    items : List<SwipeItem>,
    isKeyboardOpen : Boolean,
    itemModifier: Modifier = Modifier,
    onDeleteClick : (SwipeItem) -> Unit,
    onNameChange : (SwipeItem, String) -> Unit
) = items(
    items = items,
    key = {it.getItemId()},
    itemContent = {item: SwipeItem ->
        SwipeCard(
            item = item,
            isKeyboardOpen = isKeyboardOpen,
            isDragging = false,
            modifier = itemModifier,
            onDeleteClick = onDeleteClick,
            onNameChange = onNameChange
        )
    }
)