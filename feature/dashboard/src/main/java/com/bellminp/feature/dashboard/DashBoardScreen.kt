package com.bellminp.feature.dashboard

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bellminp.core.designsystem.utils.textSp
import com.bellminp.core.model.data.Category

@Composable
fun DashBoardRoute(
    modifier: Modifier = Modifier,
    viewModel: DashBoardViewModel = hiltViewModel()
) {

    val categoryUiState: CategoryUiState by viewModel.categoryUiState.collectAsStateWithLifecycle()

    DashBoardScreen(
        modifier = modifier,
        categoryUiState = categoryUiState
    )
}

@Composable
fun DashBoardScreen(
    modifier: Modifier = Modifier,
    categoryUiState: CategoryUiState
) {

    var tabId by rememberSaveable {
        mutableStateOf<Long?>(null)
    }

    LaunchedEffect(tabId) {
        Log.d("qweqwe", tabId.toString())
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
                is CategoryUiState.Success -> {
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

                is CategoryUiState.Loading -> {}
                is CategoryUiState.Error -> {}
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
        backgroundColor = Color.White,
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

