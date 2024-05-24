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
import com.bellminp.core.model.data.Setting

@Composable
fun DashBoardRoute(
    modifier: Modifier = Modifier,
    viewModel: DashBoardViewModel = hiltViewModel()
) {

    val dashBoardUiState = viewModel.viewState.value

    DashBoardScreen(
        modifier = modifier,
        dashBoardUiState = dashBoardUiState,
        onSelectedId = {
            viewModel.setEvent(DashBoardContract.Event.OnSelectedId(it))
        }
    )
}

@Composable
fun DashBoardScreen(
    modifier: Modifier = Modifier,
    dashBoardUiState: DashBoardContract.DashBoardUiState,
    onSelectedId : (Long?) -> Unit
) {

    var tabId by rememberSaveable {
        mutableStateOf<Long?>(null)
    }

    fun List<Setting>.findIndex(id: Long?) = indexOfFirst { setting ->
        setting == find { it.id == id }
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
                when (dashBoardUiState) {
                    is DashBoardContract.DashBoardUiState.Success -> {
                        if (dashBoardUiState.setting.isNotEmpty()) {
                            Tab(
                                tabIndex = dashBoardUiState.setting.findIndex(tabId),
                                tabItemList = dashBoardUiState.setting.map { it.name },
                                onTabIndexChange = { tabId = dashBoardUiState.setting[it].id }
                            )
                        }else{
                            tabId = null
                        }
                    }

                    is DashBoardContract.DashBoardUiState.Loading -> {}
                    is DashBoardContract.DashBoardUiState.Error -> {}
                }
            }

        }
    }

}

