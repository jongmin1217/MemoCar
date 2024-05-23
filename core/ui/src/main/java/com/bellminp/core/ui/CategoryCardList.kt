package com.bellminp.core.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import com.bellminp.core.model.data.Category
import com.bellminp.core.model.data.SwipeItem
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.ReorderableLazyListState
import org.burnoutcrew.reorderable.detectReorderAfterLongPress


fun LazyListScope.categoryCardList(
    items : List<SwipeItem>,
    isKeyboardOpen : Boolean,
    reorderableState : ReorderableLazyListState,
    itemModifier: Modifier = Modifier,
    onDeleteClick : (SwipeItem) -> Unit,
    onNameChange : (SwipeItem, String) -> Unit
) = items(
    items = items,
    key = {it.getItemId()},
    itemContent = {item: SwipeItem ->
        ReorderableItem(
            state = reorderableState,
            key = item.getItemId()
        ) { isDragging ->
            SwipeCard(
                item = item,
                isKeyboardOpen = isKeyboardOpen,
                isDragging = isDragging,
                modifier = itemModifier,
                onDeleteClick = onDeleteClick,
                onNameChange = onNameChange
            )
        }
    }
)