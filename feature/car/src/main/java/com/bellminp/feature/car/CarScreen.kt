package com.bellminp.feature.car

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bellminp.core.designsystem.component.HeaderScreen
import com.bellminp.core.designsystem.component.LabelTextField
import com.bellminp.core.designsystem.utils.keyboardAsState
import com.bellminp.core.model.data.CarInfoType
import com.bellminp.core.ui.VerticalCheckList


@Composable
fun CarRoute(
    modifier: Modifier = Modifier,
    viewModel: CarViewModel = hiltViewModel(),
    onBackClick : () -> Unit
){
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
    onBackClick : () -> Unit
){
    val isKeyboardOpen by keyboardAsState()
    val focusManager = LocalFocusManager.current

    val selectedIds = List(8){ remember { mutableStateOf<Long?>(null)} }
    
    var modelNameText by remember { mutableStateOf("") }


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
            ){

                carUiState.getUiState()?.let { uiState ->
                    val checkList = listOf(
                        uiState.category to stringResource(id = R.string.category),
                        uiState.brand to stringResource(id = R.string.brand),
                        uiState.setting.filter { it.type == CarInfoType.ENGINE.id } to CarInfoType.ENGINE.text,
                        uiState.setting.filter { it.type == CarInfoType.SUPERCHARGING.id } to CarInfoType.SUPERCHARGING.text,
                        uiState.setting.filter { it.type == CarInfoType.ENGINE_POSITION.id } to CarInfoType.ENGINE_POSITION.text,
                        uiState.setting.filter { it.type == CarInfoType.DRIVE_METHOD.id } to CarInfoType.DRIVE_METHOD.text,
                        uiState.setting.filter { it.type == CarInfoType.TRANSMISSION.id } to CarInfoType.TRANSMISSION.text,
                        uiState.setting.filter { it.type == CarInfoType.DESIGN.id } to CarInfoType.DESIGN.text
                    )

                    checkList.forEachIndexed { index, data ->
                        val (swipeItems, title) = data
                        VerticalCheckList(
                            modifier = Modifier
                                .padding(top = 20.dp),
                            list = swipeItems,
                            title = title,
                            selectId = selectedIds[index].value,
                            onCLick = { id ->
                                selectedIds[index].value = id
                            }
                        )
                    }
                }

                LabelTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    text = modelNameText,
                    labelText = stringResource(id = R.string.model_name),
                    isDecimal = false,
                    keyboardType = KeyboardType.Text,
                    onChangeText = {
                        modelNameText = it
                    }
                )
            }
        }
    }
}



fun CarContract.CarUiState.getUiState() = when(this){
    is CarContract.CarUiState.Success -> this
    is CarContract.CarUiState.Error -> null
    is CarContract.CarUiState.Loading -> null
}