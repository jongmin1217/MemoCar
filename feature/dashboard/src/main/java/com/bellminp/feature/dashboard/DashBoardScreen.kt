package com.bellminp.feature.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bellminp.core.designsystem.utils.textSp
import com.bellminp.core.model.data.Category

@Composable
fun DashBoardRoute(
    modifier: Modifier = Modifier,
    viewModel: DashBoardViewModel = hiltViewModel()
) {

    val categoryUiState: DashBoardContract.CategoryUiState = viewModel.viewState.value

    DashBoardScreen(
        modifier = modifier,
        categoryUiState = categoryUiState,
        onSelectedId = {
            viewModel.setEvent(DashBoardContract.Event.OnSelectedId(it))
        }
    )
}

@Composable
fun DashBoardScreen(
    modifier: Modifier = Modifier,
    categoryUiState: DashBoardContract.CategoryUiState,
    onSelectedId : (Long?) -> Unit
) {

    var tabId by rememberSaveable {
        mutableStateOf<Long?>(null)
    }

    fun List<Category>.findIndex(id: Long?) = indexOfFirst { category ->
        category == find { it.id == id }
    }.let {
        if (it == -1) {
            tabId = get(0).id
            0
        } else {
            it
        }
    }

    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        Column(
            Modifier.fillMaxSize()
        ) {
            when (categoryUiState) {
                is DashBoardContract.CategoryUiState.Success -> {
                    if (categoryUiState.category.isNotEmpty()) {
                        CategoryTab(
                            tabIndex = categoryUiState.category.findIndex(tabId),
                            categoryList = categoryUiState.category,
                            onTabIdChange = { tabId = it }
                        )
                    }else{
                        tabId = null
                    }
                }

                is DashBoardContract.CategoryUiState.Loading -> {}
                is DashBoardContract.CategoryUiState.Error -> {}
            }
        }

    }
}

@Composable
fun CategoryTab(
    modifier: Modifier = Modifier,
    tabIndex: Int,
    categoryList: List<Category>,
    onTabIdChange: (Long) -> Unit
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
                    .background(MaterialTheme.colorScheme.primaryContainer)
            )
        },
        contentColor = Color.White,
        containerColor = Color.White,
        divider = {},
        modifier = modifier.fillMaxWidth()
    ) {
        categoryList.forEachIndexed { index, category ->
            val isSelected = index == tabIndex
            Tab(
                selected = isSelected,
                onClick = {
                    onTabIdChange(categoryList[index].id)
                },
                modifier = Modifier
                    .height(54.dp)
                    .background(Color.White),
            ) {
                Text(
                    text = category.name,
                    style = TextStyle(
                        fontSize = 15.dp.textSp,
                        fontWeight = if (isSelected) FontWeight.W800 else FontWeight.W600,
                        color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color.Black
                    )
                )
            }
        }
    }
}

