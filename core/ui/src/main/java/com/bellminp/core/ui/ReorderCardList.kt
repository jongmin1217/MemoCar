package com.bellminp.core.ui

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import com.bellminp.core.model.data.Item
import com.bellminp.core.model.data.SelectBoxData
import com.bellminp.core.model.data.Setting
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.ReorderableLazyListState
import org.burnoutcrew.reorderable.detectReorderAfterLongPress


fun LazyListScope.reorderCardList(
    items : List<Item>,
    itemModifier: Modifier = Modifier,
    reorderableLazyListState: ReorderableLazyListState,
    onClick : (Item) -> Unit,
    onDeleteClick : (Item) -> Unit
) = items(
    items = items,
    key = { it.id },
    itemContent = { item ->
        ReorderableItem(
            reorderableState = reorderableLazyListState,
            key = {item.id}
        ) {isDragging->
            ReorderCard(
                item = item,
                modifier = itemModifier,
                isDragging = isDragging,
                onClick = onClick,
                onDeleteClick = onDeleteClick
            )
        }
    }
)