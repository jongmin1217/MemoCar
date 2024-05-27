package com.bellminp.core.ui

import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.Modifier
import com.bellminp.core.model.data.SelectBoxData
import com.bellminp.core.model.data.Setting

fun LazyGridScope.selectCardList(
    items : List<SelectBoxData>,
    selectId : Long?,
    itemModifier: Modifier = Modifier,
    onCLick : (Long) -> Unit
) = items(
    items = items,
    key = { it.id },
    itemContent = { item : SelectBoxData ->
        SelectCard(
            item = item,
            selectId = selectId,
            modifier = itemModifier,
            onCLick = onCLick
        )
    }
)