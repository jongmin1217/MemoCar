package com.bellminp.core.ui

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import com.bellminp.core.model.data.Setting


fun LazyListScope.swipeCardList(
    items : List<Setting>,
    isKeyboardOpen : Boolean,
    itemModifier: Modifier = Modifier,
    onDeleteClick : (Setting) -> Unit,
    onNameChange : (Setting, String) -> Unit
) = items(
    items = items,
    key = {it.id},
    itemContent = {item: Setting ->
        SwipeCard(
            item = item,
            isKeyboardOpen = isKeyboardOpen,
            modifier = itemModifier,
            onDeleteClick = onDeleteClick,
            onNameChange = onNameChange
        )
    }
)