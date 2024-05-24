package com.bellminp.core.ui

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items


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