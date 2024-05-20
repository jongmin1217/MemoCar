package com.bellminp.core.ui

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import com.bellminp.core.model.data.Category

fun LazyListScope.categoryCardList(
    items : List<Category>,
    itemModifier: Modifier = Modifier
) = items(
    items = items,
    key = {it.id},
    itemContent = {item: Category ->
        CategoryCard(
            category = item,
            modifier = itemModifier
        )
    }
)