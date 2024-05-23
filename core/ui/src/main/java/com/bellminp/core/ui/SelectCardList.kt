package com.bellminp.core.ui

import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.Modifier
import com.bellminp.core.model.data.SwipeItem

fun LazyGridScope.selectCardList(
    items : List<SwipeItem>,
    selectId : Long?,
    itemModifier: Modifier = Modifier,
    onCLick : (Long) -> Unit
) = items(
    items = items,
    key = { it.getItemId() },
    itemContent = { item : SwipeItem ->
        SelectCard(
            item = item,
            selectId = selectId,
            modifier = itemModifier,
            onCLick = onCLick
        )
    }
)