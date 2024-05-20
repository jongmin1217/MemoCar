package com.bellminp.feature.category

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bellminp.core.common.result.Result
import com.bellminp.core.designsystem.utils.keyboardAsState
import com.bellminp.core.designsystem.utils.textSp
import com.bellminp.core.ui.categoryCardList
import java.util.Locale.Category

@Composable
fun CategoryRoute(
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel = hiltViewModel(),
) {

    val categoryUiState: CategoryUiState by viewModel.categoryUiState.collectAsStateWithLifecycle()

    CategoryScreen(
        modifier = modifier,
        categoryUiState = categoryUiState,
        onAddClick = {
            viewModel.insertCategory(it)
        }
    )
}

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    categoryUiState: CategoryUiState,
    onAddClick: (String) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = Color.White
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            item {
                Column {
                    AddCategoryComponent(onAddClick = onAddClick)
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }

            when (categoryUiState) {
                is CategoryUiState.Success -> {
                    categoryCardList(categoryUiState.category)
                }

                is CategoryUiState.Loading -> {

                }

                is CategoryUiState.Error -> {

                }
            }

        }
    }
}

@Composable
fun AddCategoryComponent(
    modifier: Modifier = Modifier,
    onAddClick: (String) -> Unit
) {

    val isKeyboardOpen by keyboardAsState()
    val focusManager = LocalFocusManager.current

    var categoryText by remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = isKeyboardOpen) {
        if (isKeyboardOpen.not()) focusManager.clearFocus(true)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 20.dp)
            .background(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.primaryContainer
            )
    ) {
        BasicTextField(
            value = categoryText,
            onValueChange = {
                categoryText = it
            },
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
                .align(Alignment.CenterVertically),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.dp.textSp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Start
            )
        )

        Button(
            onClick = {
                onAddClick(categoryText)
                categoryText = ""
            },
            modifier = Modifier
                .width(80.dp)
                .fillMaxHeight(),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 8.dp,
                bottomStart = 0.dp,
                bottomEnd = 8.dp
            ),
            colors = ButtonColors(
                contentColor = Color.Blue,
                containerColor = Color.Blue,
                disabledContentColor = Color.LightGray,
                disabledContainerColor = Color.LightGray
            ),
            enabled = categoryText.isNotBlank()
        ) {
            Text(
                text = "추가",
                style = TextStyle(color = Color.White)
            )
        }
    }
}
