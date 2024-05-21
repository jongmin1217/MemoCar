package com.bellminp.core.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import com.bellminp.core.model.data.Category
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.ReorderableLazyListState
import org.burnoutcrew.reorderable.detectReorderAfterLongPress

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.categoryCardList(
    items : List<Category>,
    isKeyboardOpen : Boolean,
    reorderableState : ReorderableLazyListState,
    itemModifier: Modifier = Modifier,
    onDeleteClick : (Category) -> Unit,
    onNameChange : (Category) -> Unit
) = items(
    items = items,
    key = {it.id},
    itemContent = {item: Category ->
        ReorderableItem(
            state = reorderableState,
            key = item.id
        ) { isDragging ->
            CategoryCard(
                category = item,
                isKeyboardOpen = isKeyboardOpen,
                isDragging = isDragging,
                modifier = itemModifier,
                onDeleteClick = onDeleteClick,
                onNameChange = onNameChange
            )
        }
    }
)