package com.bellminp.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bellminp.core.designsystem.utils.textSp


@Composable
fun Tab(
    modifier: Modifier = Modifier,
    tabIndex: Int,
    tabItemList: List<String>,
    onTabIndexChange: (Int) -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = tabIndex,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            Spacer(
                modifier = Modifier
                    .tabIndicatorOffset(
                        currentTabPosition = tabPositions[tabIndex]
                    )
                    .height(2.dp)
                    .background(MaterialTheme.colorScheme.primary)
            )
        },
        contentColor = Color.White,
        containerColor = Color.White,
        divider = {},
        modifier = modifier.fillMaxWidth()
    ) {
        tabItemList.forEachIndexed { index, tabItem ->
            val isSelected = index == tabIndex
            Tab(
                selected = isSelected,
                onClick = {
                    onTabIndexChange(index)
                },
                modifier = Modifier
                    .height(54.dp)
                    .background(Color.White),
            ) {
                Text(
                    text = tabItem,
                    style = TextStyle(
                        fontSize = 15.dp.textSp,
                        fontWeight = if (isSelected) FontWeight.W800 else FontWeight.W600,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black
                    )
                )
            }
        }
    }
}
