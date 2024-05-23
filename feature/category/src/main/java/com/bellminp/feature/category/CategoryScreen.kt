package com.bellminp.feature.category

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bellminp.core.designsystem.component.AddBtn
import com.bellminp.core.designsystem.utils.keyboardAsState
import com.bellminp.core.model.data.Category
import com.bellminp.core.ui.categoryCardList
import com.bellminp.core.ui.swipeLoadingCardList
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable

@Composable
fun CategoryRoute(
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel = hiltViewModel(),
) {

    val categoryUiState: CategoryContract.CategoryUiState = viewModel.viewState.value

    CategoryScreen(
        modifier = modifier,
        categoryUiState = categoryUiState,
        onAddClick = {
            viewModel.setEvent(CategoryContract.Event.OnInsertCategory)
        },
        onDeleteClick = {
            viewModel.setEvent(CategoryContract.Event.OnDeleteCategory(it))
        },
        onNameChange = { category, changedName ->
            viewModel.setEvent(CategoryContract.Event.OnUpdateNameCategory(category, changedName))
        },
        onMoveCategory = {
            viewModel.setEvent(CategoryContract.Event.OnMoveCategory(it))
        }
    )
}

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    categoryUiState: CategoryContract.CategoryUiState,
    onAddClick: () -> Unit,
    onDeleteClick: (Category) -> Unit,
    onNameChange: (Category, String) -> Unit,
    onMoveCategory: (List<Category>) -> Unit,
) {

    val isKeyboardOpen by keyboardAsState()
    var data by remember { mutableStateOf<List<Category>>(listOf()) }

    val lazyListState = rememberLazyListState()
    val state = rememberReorderableLazyListState(
        listState = lazyListState,
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
            is CategoryContract.CategoryUiState.Success -> {
                data = categoryUiState.category
            }

            is CategoryContract.CategoryUiState.Loading -> {}
            is CategoryContract.CategoryUiState.Error -> {}
        }
    }

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
                    onDeleteClick = { if(it is Category) onDeleteClick(it) },
                    onNameChange = { category, changedName ->
                        if(category is Category) onNameChange(category,changedName)
                    }
                )

                if(categoryUiState is CategoryContract.CategoryUiState.Loading) swipeLoadingCardList()

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
                            onClick = onAddClick
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }

}

