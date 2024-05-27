package com.bellminp.feature

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bellminp.core.designsystem.component.HeaderScreen
import com.bellminp.core.designsystem.component.LabelTextField
import com.bellminp.core.designsystem.utils.keyboardAsState
import com.bellminp.core.designsystem.utils.textSp
import com.bellminp.core.model.data.Align
import com.bellminp.core.model.data.Format
import com.bellminp.core.model.data.InputStateType
import com.bellminp.core.model.data.InputType
import com.bellminp.core.model.data.Item
import com.bellminp.core.model.data.SelectBoxData
import com.bellminp.core.model.data.SelectStateType
import com.bellminp.core.model.data.Type
import com.bellminp.core.model.data.toSelectData
import com.bellminp.core.ui.VerticalCheckList
import com.bellminp.feature.additem.R


@Composable
fun AddItemRoute(
    modifier: Modifier = Modifier,
    viewModel: AddItemViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {

    CarScreen(
        modifier = modifier,
        onAddClick = { viewModel.setEvent(AddItemContract.Event.OnInsert(it)) },
        onBackClick = onBackClick
    )

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collect{
            when(it){
                is AddItemContract.Effect.InsertSuccess -> onBackClick()
                is AddItemContract.Effect.InsertError -> {}
            }
        }
    }

}


@Composable
fun CarScreen(
    modifier: Modifier = Modifier,
    onAddClick : (Item) -> Unit,
    onBackClick: () -> Unit,
) {

    val isKeyboardOpen by keyboardAsState()
    val focusManager = LocalFocusManager.current

    var titleText by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue(
                text = "",
                selection = TextRange(0)
            )
        )
    }

    var typeValue by rememberSaveable { mutableStateOf<Long?>(null) }
    var enableSave by rememberSaveable { mutableStateOf(false) }

    var subText by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue(
                text = "",
                selection = TextRange(0)
            )
        )
    }

    val writeTypeList = listOf(
        Triple(
            listOf(Format.TEXT.toSelectData(), Format.NUMBER.toSelectData()),
            stringResource(id = R.string.format_type),
            rememberSaveable { mutableStateOf<Long?>(null) }
        ),
        Triple(
            listOf(
                SelectBoxData(id = 0, name = stringResource(id = R.string.yes)),
                SelectBoxData(id = 1, name = stringResource(id = R.string.no))
            ),
            stringResource(id = R.string.isDecimal_type),
            rememberSaveable { mutableStateOf<Long?>(null) }
        ),
        Triple(
            listOf(
                Align.LEFT.toSelectData(),
                Align.CENTER.toSelectData(),
                Align.RIGHT.toSelectData()
            ),
            stringResource(id = R.string.align_type),
            rememberSaveable { mutableStateOf<Long?>(null) }
        )
    )

    val selectTypeList = listOf(
        Triple(
            listOf(
                SelectBoxData(id = 0, name = stringResource(id = R.string.yes)),
                SelectBoxData(id = 1, name = stringResource(id = R.string.no))
            ),
            stringResource(id = R.string.isShowDashBoard_type),
            rememberSaveable { mutableStateOf<Long?>(null) }
        ),
        Triple(
            listOf(
                SelectBoxData(id = 0, name = stringResource(id = R.string.yes)),
                SelectBoxData(id = 1, name = stringResource(id = R.string.no))
            ),
            stringResource(id = R.string.hasImage_type),
            rememberSaveable { mutableStateOf<Long?>(null) }
        )
    )


    LaunchedEffect(key1 = isKeyboardOpen) {
        if (isKeyboardOpen.not()) {
            focusManager.clearFocus()
        }
    }


    LaunchedEffect(
        titleText,
        typeValue,
        writeTypeList.map { it.third.value },
        selectTypeList.map { it.third.value }
    ) {
        enableSave = titleText.text.isNotBlank() && when (typeValue) {
            Type.WRITE.value.toLong() -> {
                writeTypeList.map { it.third.value }.none { it == null }
            }

            Type.SELECT.value.toLong() -> {
                selectTypeList.map { it.third.value }.none { it == null }
            }

            Type.IMAGE.value.toLong() -> true
            else -> false
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
                titleRes = R.string.add_item,
                actionContent = {
                    Text(
                        text = stringResource(id = R.string.save),
                        style = TextStyle(
                            fontSize = 16.dp.textSp,
                            color = if (enableSave) MaterialTheme.colorScheme.primary else Color.LightGray,
                            fontWeight = if (enableSave) FontWeight.W800 else FontWeight.W500
                        ),
                        modifier = Modifier
                            .padding(end = 20.dp)
                            .clickable {
                                if (enableSave) {
                                    onAddClick(
                                        convertItem(
                                            titleText = titleText.text,
                                            type = typeValue,
                                            writeList = writeTypeList.map { it.third.value },
                                            selectList = selectTypeList.map { it.third.value },
                                            subText = subText.text
                                        )
                                    )
                                }
                            }
                    )
                },
                navigationIcon = Icons.Rounded.Close,
                navigationIconContentDescription = "close",
                onNavigationClick = onBackClick
            )

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {

                LabelTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    text = titleText,
                    labelText = stringResource(id = R.string.name),
                    onChangeText = {
                        titleText = it
                    }
                )

                VerticalCheckList(
                    modifier = Modifier
                        .padding(top = 20.dp),
                    list = listOf(
                        Type.WRITE.toSelectData(),
                        Type.SELECT.toSelectData(),
                        Type.IMAGE.toSelectData()
                    ),
                    title = stringResource(id = R.string.input_type),
                    selectId = typeValue,
                    onCLick = { id -> typeValue = id }
                )

                when (typeValue) {
                    Type.WRITE.value.toLong() -> {
                        writeTypeList.forEach {
                            VerticalCheckList(
                                modifier = Modifier
                                    .padding(top = 20.dp),
                                list = it.first,
                                title = it.second,
                                selectId = it.third.value,
                                onCLick = { id -> it.third.value = id }
                            )
                        }

                        LabelTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 10.dp),
                            text = subText,
                            labelText = stringResource(id = R.string.subText_type),
                            onChangeText = {
                                subText = it
                            }
                        )
                    }

                    Type.SELECT.value.toLong() -> {
                        selectTypeList.forEach {
                            VerticalCheckList(
                                modifier = Modifier
                                    .padding(top = 20.dp),
                                list = it.first,
                                title = it.second,
                                selectId = it.third.value,
                                onCLick = { id -> it.third.value = id }
                            )
                        }
                    }
                }
            }
        }
    }
}

fun convertItem(
    titleText : String,
    type: Long?,
    writeList : List<Long?>,
    selectList : List<Long?>,
    subText : String? = null
) = Item(
    id = 0,
    title = titleText,
    inputType = InputType(
        type = when(type){
            Type.WRITE.value.toLong() -> Type.WRITE
            Type.SELECT.value.toLong() -> Type.SELECT
            else -> Type.IMAGE
        },
        format = if(type == Type.WRITE.value.toLong()){
            when(writeList[0]){
                Format.TEXT.value.toLong() -> Format.TEXT
                else -> Format.NUMBER
            }
        }else null,
        isDecimal = if(type == Type.WRITE.value.toLong()){
            writeList[1] == 0L
        }else null,
        align = if(type == Type.WRITE.value.toLong()){
            when(writeList[2]){
                Align.LEFT.value.toLong() -> Align.LEFT
                Align.CENTER.value.toLong() -> Align.CENTER
                else -> Align.RIGHT
            }
        }else null,
        isShowDashBoard = if(type == Type.SELECT.value.toLong()){
            selectList[0] == 0L
        }else null,
        hasImage = if(type == Type.SELECT.value.toLong()){
            selectList[1] == 0L
        }else false,
        subText = subText
    ),
    0
)