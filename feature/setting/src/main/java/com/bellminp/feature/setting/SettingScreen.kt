package com.bellminp.feature.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bellminp.core.designsystem.component.AddBtn
import com.bellminp.core.designsystem.component.HeaderScreen
import com.bellminp.core.designsystem.component.Tab
import com.bellminp.core.designsystem.utils.keyboardAsState
import com.bellminp.core.model.data.CarInfoType
import com.bellminp.core.model.data.CarInfoType.DESIGN
import com.bellminp.core.model.data.CarInfoType.DRIVE_METHOD
import com.bellminp.core.model.data.CarInfoType.ENGINE
import com.bellminp.core.model.data.CarInfoType.ENGINE_POSITION
import com.bellminp.core.model.data.CarInfoType.SUPERCHARGING
import com.bellminp.core.model.data.CarInfoType.TRANSMISSION
import com.bellminp.core.model.data.Setting
import com.bellminp.core.ui.swipeCardList
import com.bellminp.core.ui.swipeLoadingCardList

@Composable
fun SettingRoute(
    modifier: Modifier = Modifier,
    viewModel: SettingViewModel = hiltViewModel(),
    onBackClick : () -> Unit
){
    val settingUiState = viewModel.viewState.value

    SettingScreen(
        modifier = modifier,
        settingUiState = settingUiState,
        onBackClick = onBackClick,
        onDeleteClick = { viewModel.setEvent(SettingContract.Event.OnDeleteSetting(it)) },
        onChangeName = { setting, changedName ->
            viewModel.setEvent(SettingContract.Event.OnUpdateNameSetting(setting, changedName))
        },
        onAddClick = { viewModel.setEvent(SettingContract.Event.OnInsertSetting(it)) },
        onChangeTab = { viewModel.setEvent(SettingContract.Event.OnChangeTab(it.toLong())) }
    )
}

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    settingUiState: SettingContract.SettingUiState,
    onBackClick : () -> Unit,
    onDeleteClick : (Setting) -> Unit,
    onChangeName : (Setting, String) -> Unit,
    onAddClick : (CarInfoType) -> Unit,
    onChangeTab : (Int) -> Unit
){

    var tabIndex by rememberSaveable { mutableIntStateOf(0) }
    val lazyListState = rememberLazyListState()
    val isKeyboardOpen by keyboardAsState()

    val tabItems = listOf(ENGINE,SUPERCHARGING,ENGINE_POSITION,DRIVE_METHOD,TRANSMISSION,DESIGN)

    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        Column(
            Modifier.fillMaxSize()
        ) {

            HeaderScreen(
                titleRes = R.string.setting,
                navigationIcon = Icons.Rounded.Close,
                navigationIconContentDescription = "close",
                onNavigationClick = onBackClick
            )

            Tab(
                tabIndex = tabIndex,
                tabItemList = tabItems.map { it.text },
                onTabIndexChange = {
                    tabIndex = it
                    onChangeTab(tabIndex)
                }
            )

            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                when(settingUiState){
                    is SettingContract.SettingUiState.Success -> {
                        swipeCardList(
                            items = settingUiState.setting,
                            isKeyboardOpen = isKeyboardOpen,
                            onDeleteClick = { if(it is Setting) onDeleteClick(it) },
                            onNameChange = { brand, changedName ->
                                if(brand is Setting) onChangeName(brand, changedName)
                            }
                        )
                    }
                    is SettingContract.SettingUiState.Loading -> {}
                    is SettingContract.SettingUiState.Error -> {}
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))

                        AddBtn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .padding(horizontal = 20.dp),
                            onClick = { onAddClick(tabItems[tabIndex]) }
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }
}