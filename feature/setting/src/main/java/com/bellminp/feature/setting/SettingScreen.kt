package com.bellminp.feature.setting

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bellminp.core.designsystem.utils.keyboardAsState
import com.bellminp.core.model.data.SelectStateType
import com.bellminp.core.model.data.Setting
import com.bellminp.core.ui.HorizontalTabPager
import kotlinx.coroutines.launch
import com.bellminp.core.model.data.SelectStateType.*

@Composable
fun SettingRoute(
    modifier: Modifier = Modifier,
    viewModel: SettingViewModel = hiltViewModel(),
) {
    val settingUiState = viewModel.viewState.value
    Log.d("qweqwe",settingUiState.toString())

    SettingScreen(
        modifier = modifier,
        settingUiState = settingUiState,
        onDeleteClick = { viewModel.setEvent(SettingContract.Event.OnDeleteSetting(it)) },
        onChangeName = { setting, changedName ->
            viewModel.setEvent(SettingContract.Event.OnUpdateNameSetting(setting, changedName))
        },
        onAddClick = { viewModel.setEvent(SettingContract.Event.OnInsertSetting(it)) },
        onUpsert = { viewModel.setEvent(SettingContract.Event.OnMoveSetting(it)) }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    settingUiState: SettingContract.SettingUiState,
    onDeleteClick: (Setting) -> Unit,
    onChangeName: (Setting, String) -> Unit,
    onAddClick: (Int) -> Unit,
    onUpsert : (List<Setting>) -> Unit
) {

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState { 8 }
    val isKeyboardOpen by keyboardAsState()

    Scaffold(
        contentColor = Color.White,
        containerColor = Color.White
    ) { paddingValue ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(top = paddingValue.calculateTopPadding() + 64.dp)
                .imePadding(),
            color = Color.White
        ) {
            when (settingUiState) {
                is SettingContract.SettingUiState.Success -> {
                    HorizontalTabPager(
                        pagerState = pagerState,
                        itemList = settingUiState.settingList,
                        isKeyboardOpen = isKeyboardOpen,
                        onTabIndexChange = {
                            scope.launch {
                                pagerState.animateScrollToPage(it)
                            }
                        },
                        onAddClick = onAddClick,
                        onDeleteClick = onDeleteClick,
                        onChangeName = onChangeName,
                        onUpsert = onUpsert
                    )
                }

                is SettingContract.SettingUiState.Error -> {}
                is SettingContract.SettingUiState.Loading -> {}
            }
        }
    }
}