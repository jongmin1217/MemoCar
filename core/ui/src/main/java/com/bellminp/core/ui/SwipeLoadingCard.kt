package com.bellminp.core.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bellminp.core.designsystem.component.ShimmerComponent


@Composable
fun SwipeLoadingCard() {
    ShimmerComponent(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(
                horizontal = 20.dp,
                vertical = 10.dp
            ),
        radius = 8.dp
    )
}