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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bellminp.core.common.result.Result
import com.bellminp.core.designsystem.component.AddBtn
import com.bellminp.core.designsystem.utils.keyboardAsState
import com.bellminp.core.designsystem.utils.textSp
import com.bellminp.core.model.data.Category
import com.bellminp.core.ui.CategoryCard
import com.bellminp.core.ui.categoryCardList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.burnoutcrew.reorderable.ItemPosition
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable

@Composable
fun CategoryRoute(
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val categoryUiState: CategoryUiState by viewModel.categoryUiState.collectAsStateWithLifecycle()

    CategoryScreen(
        modifier = modifier,
        categoryUiState = categoryUiState,
        onAddClick = {
            viewModel.insertCategory(context.resources.getString(R.string.feature_category))
        },
        onDeleteClick = {
            viewModel.deleteCategory(listOf(it))
        },
        onNameChange = viewModel::updateCategory,
        onMoveCategory = viewModel::moveCategory
    )
}

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    categoryUiState: CategoryUiState,
    onAddClick: () -> Unit,
    onDeleteClick: (Category) -> Unit,
    onNameChange: (Category) -> Unit,
    onMoveCategory: (List<Category>) -> Unit,
) {

    val isKeyboardOpen by keyboardAsState()
    var data by remember { mutableStateOf<List<Category>>(listOf()) }
    val state = rememberReorderableLazyListState(
        onMove = { from, to ->
            data = data.toMutableList().apply {
                add(to.index, removeAt(from.index))
            }
        },
        canDragOver = { draggedOver, _ ->
            draggedOver.index <= data.lastIndex
        },
        onDragEnd = { _, _ ->
            onMoveCategory(data)
        }
    )

    LaunchedEffect(categoryUiState) {
        when (categoryUiState) {
            is CategoryUiState.Success -> {
                data = categoryUiState.category
            }

            is CategoryUiState.Loading -> {}
            is CategoryUiState.Error -> {}
        }
    }

    Surface(
        modifier = modifier
            .fillMaxSize()
            .imePadding(),
        color = Color.White
    ) {

        LazyColumn(
            state = state.listState,
            modifier = Modifier
                .fillMaxSize()
                .reorderable(state)
                .detectReorderAfterLongPress(state)
        ) {
            categoryCardList(
                items = data,
                isKeyboardOpen = isKeyboardOpen,
                reorderableState = state,
                onDeleteClick = onDeleteClick,
                onNameChange = onNameChange
            )

            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.height(10.dp))

                    AddCategoryComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(horizontal = 20.dp),
                        onAddClick = onAddClick
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
fun AddCategoryComponent(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit,
) {
    AddBtn(
        modifier = modifier,
        onClick = onAddClick
    )
}
