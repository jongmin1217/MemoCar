package com.bellminp.core.ui

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import com.bellminp.core.model.data.SwipeItem


fun LazyListScope.swipeLoadingCardList(
    count : Int = 3,
    items : List<Int> = List(count){it}
) = items(
    items = items,
    key = {it},
    itemContent = {_ ->
        SwipeLoadingCard()
    }
)