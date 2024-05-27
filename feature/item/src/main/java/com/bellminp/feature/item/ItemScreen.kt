package com.bellminp.feature.item

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bellminp.core.model.data.Item
import com.bellminp.core.ui.HorizontalTabPager
import com.bellminp.core.ui.reorderCardList
import kotlinx.coroutines.launch
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable

@Composable
fun ItemRoute(
    modifier: Modifier = Modifier,
    viewModel: ItemViewModel = hiltViewModel(),
) {
    val itemUiState = viewModel.viewState.value

    ItemScreen(
        modifier = modifier,
        itemUiState = itemUiState
    )
}

@Composable
fun ItemScreen(
    modifier: Modifier = Modifier,
    itemUiState: ItemContract.ItemUiState,
) {

    var data by remember { mutableStateOf<List<Item>>(listOf()) }

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
            //onMoveCategory(data)
        }
    )

    LaunchedEffect(key1 = itemUiState) {
        if (itemUiState is ItemContract.ItemUiState.Success) {
            data = itemUiState.itemList
            Log.d("qweqwe","$data")
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
                    .then(Modifier.reorderable(state))
            ) {
                reorderCardList(
                    items = data,
                    itemModifier = Modifier.detectReorderAfterLongPress(state),
                    reorderableLazyListState = state,
                    onClick = {

                    },
                    onDeleteClick = {

                    }
                )
            }
        }
    }
}