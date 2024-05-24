package com.bellminp.feature.car

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.bellminp.core.designsystem.component.HeaderScreen
import com.bellminp.core.designsystem.utils.keyboardAsState
import com.bellminp.core.model.data.InputStateType
import com.bellminp.core.model.data.SelectStateType
import com.bellminp.core.model.data.Setting


@Composable
fun CarRoute(
    modifier: Modifier = Modifier,
    viewModel: CarViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val carUiState = viewModel.viewState.value

    CarScreen(
        modifier = modifier,
        carUiState = carUiState,
        onBackClick = onBackClick
    )
}

@Composable
fun CarScreen(
    modifier: Modifier = Modifier,
    carUiState: CarContract.CarUiState,
    onBackClick: () -> Unit
) {
    val isKeyboardOpen by keyboardAsState()
    val focusManager = LocalFocusManager.current
    val screenWidth = LocalConfiguration.current.screenWidthDp

    val selectStateList = List(8) { rememberSaveable { mutableStateOf<Long?>(null) } }
    val inputStateList = List(7) {
        rememberSaveable(stateSaver = TextFieldValue.Saver) {
            mutableStateOf(
                TextFieldValue(
                    text = "",
                    selection = TextRange.Zero
                )
            )
        }
    }


    LaunchedEffect(key1 = isKeyboardOpen) {
        if (isKeyboardOpen.not()) {
            focusManager.clearFocus()
        }
    }

    Surface(
        modifier = modifier
            .fillMaxSize()
            .imePadding(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            HeaderScreen(
                titleRes = R.string.add_car,
                navigationIcon = Icons.Rounded.Close,
                navigationIconContentDescription = "close",
                onNavigationClick = onBackClick
            )

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {

                carUiState.getUiState()?.let { uiState ->
//                    val checkList = listOf(
//                        CarComponent.Select(
//                            uiState.category,
//                            SelectStateType.CATEGORY,
//                            selectStateList[SelectStateType.CATEGORY.index]
//                        ),
//                        CarComponent.Select(
//                            uiState.brand,
//                            SelectStateType.BRAND,
//                            selectStateList[SelectStateType.BRAND.index]
//                        ),
//                        CarComponent.Input(
//                            InputStateType.NAME,
//                            inputStateList[InputStateType.NAME.index]
//                        ),
//                        CarComponent.Input(
//                            InputStateType.HP,
//                            inputStateList[InputStateType.HP.index]
//                        ),
//                        CarComponent.Select(
//                            uiState.setting.filter { it.type == CarInfoType.ENGINE.id },
//                            SelectStateType.ENGINE,
//                            selectStateList[SelectStateType.ENGINE.index]
//                        ),
//                        CarComponent.Input(
//                            InputStateType.ENGINE_CC,
//                            inputStateList[InputStateType.ENGINE_CC.index]
//                        ),
//                        CarComponent.Select(
//                            uiState.setting.filter { it.type == CarInfoType.SUPERCHARGING.id },
//                            SelectStateType.SUPERCHARGING,
//                            selectStateList[SelectStateType.SUPERCHARGING.index]
//                        ),
//                        CarComponent.Select(
//                            uiState.setting.filter { it.type == CarInfoType.ENGINE_POSITION.id },
//                            SelectStateType.ENGINE_POSITION,
//                            selectStateList[SelectStateType.ENGINE_POSITION.index]
//                        ),
//                        CarComponent.Select(
//                            uiState.setting.filter { it.type == CarInfoType.DRIVE_METHOD.id },
//                            SelectStateType.DRIVE_METHOD,
//                            selectStateList[SelectStateType.DRIVE_METHOD.index]
//                        ),
//                        CarComponent.Input(
//                            InputStateType.TOP_SPEED,
//                            inputStateList[InputStateType.TOP_SPEED.index]
//                        ),
//                        CarComponent.Input(
//                            InputStateType.ACCELERATION_PERFORMANCE,
//                            inputStateList[InputStateType.ACCELERATION_PERFORMANCE.index]
//                        ),
//                        CarComponent.Select(
//                            uiState.setting.filter { it.type == CarInfoType.TRANSMISSION.id },
//                            SelectStateType.TRANSMISSION,
//                            selectStateList[SelectStateType.TRANSMISSION.index]
//                        ),
//                        CarComponent.Select(
//                            uiState.setting.filter { it.type == CarInfoType.DESIGN.id },
//                            SelectStateType.DESIGN,
//                            selectStateList[SelectStateType.DESIGN.index]
//                        ),
//                        CarComponent.Input(
//                            InputStateType.PRICE,
//                            inputStateList[InputStateType.PRICE.index]
//                        ),
//                        CarComponent.Input(
//                            InputStateType.RESELL_PRICE,
//                            inputStateList[InputStateType.RESELL_PRICE.index]
//                        )
//                    )
//
//                    checkList.forEach { data ->
//                        when (data) {
//                            is CarComponent.Select -> {
//                                VerticalCheckList(
//                                    modifier = Modifier
//                                        .padding(top = 20.dp),
//                                    list = data.list,
//                                    title = data.data.title,
//                                    selectId = data.state.value,
//                                    onCLick = { id -> data.state.value = id }
//                                )
//                            }
//
//                            is CarComponent.Input -> {
//                                LabelTextField(
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .padding(horizontal = 20.dp, vertical = 10.dp),
//                                    text = data.state.value,
//                                    labelText = data.data.label,
//                                    isDecimal = data.data.isDecimal,
//                                    descriptionText = data.data.descriptionText,
//                                    keyboardType = if (data.data.isNumber) KeyboardType.Number else KeyboardType.Text,
//                                    onChangeText = { data.state.value = it }
//                                )
//                            }
//                        }
//                    }
//
//                    ColorPicker(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(20.dp)
//                    )
                }
            }
        }
    }
}

sealed class CarComponent {
    data class Select(
        val list: List<Setting>,
        val data: SelectStateType,
        val state: MutableState<Long?>
    ) : CarComponent()

    data class Input(
        val data: InputStateType,
        val state: MutableState<TextFieldValue>
    ) : CarComponent()
}


fun CarContract.CarUiState.getUiState() = when (this) {
    is CarContract.CarUiState.Success -> this
    is CarContract.CarUiState.Error -> null
    is CarContract.CarUiState.Loading -> null
}