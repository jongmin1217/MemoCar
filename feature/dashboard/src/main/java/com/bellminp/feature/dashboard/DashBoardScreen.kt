package com.bellminp.feature.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bellminp.core.designsystem.component.Tab
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

    Scaffold(
        contentColor = Color.White,
        containerColor = Color.White
    ) { paddingValue ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(top = paddingValue.calculateTopPadding() + 64.dp),
            color = Color.White
        ) {
            Column(
                Modifier.fillMaxSize()
            ) {
                when (categoryUiState) {
                    is DashBoardContract.CategoryUiState.Success -> {
                        if (categoryUiState.category.isNotEmpty()) {
                            Tab(
                                tabIndex = categoryUiState.category.findIndex(tabId),
                                tabItemList = categoryUiState.category.map { it.name },
                                onTabIndexChange = { tabId = categoryUiState.category[it].id }
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

}

