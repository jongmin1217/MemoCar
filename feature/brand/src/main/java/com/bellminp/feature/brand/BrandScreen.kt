package com.bellminp.feature.brand

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bellminp.core.designsystem.component.AddBtn
import com.bellminp.core.designsystem.utils.keyboardAsState
import com.bellminp.core.model.data.Brand
import com.bellminp.core.ui.swipeCardList
import com.bellminp.core.ui.swipeLoadingCardList


@Composable
fun BrandRoute(
    modifier: Modifier = Modifier,
    viewModel: BrandViewModel = hiltViewModel(),
){
    val brandUiState = viewModel.viewState.value

    BrandScreen(
        modifier = modifier,
        brandUiState = brandUiState,
        onAddClick = {
            viewModel.setEvent(BrandContract.Event.OnInsertBrand)
        }, onDeleteClick = {
            viewModel.setEvent(BrandContract.Event.OnDeleteBrand(it))
        }, onChangeName = { brand, changedName ->
            viewModel.setEvent(BrandContract.Event.OnUpdateNameBrand(brand, changedName))
        }
    )
}

@Composable
fun BrandScreen(
    modifier: Modifier = Modifier,
    brandUiState: BrandContract.BrandUiState,
    onAddClick : () -> Unit,
    onDeleteClick : (Brand) -> Unit,
    onChangeName : (Brand, String) -> Unit
){
    val isKeyboardOpen by keyboardAsState()
    val lazyListState = rememberLazyListState()


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
                state = lazyListState,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                when(brandUiState){
                    is BrandContract.BrandUiState.Success -> {
                        swipeCardList(
                            items = brandUiState.brand,
                            isKeyboardOpen = isKeyboardOpen,
                            onDeleteClick = { if(it is Brand) onDeleteClick(it) },
                            onNameChange = { brand, changedName ->
                                if(brand is Brand) onChangeName(brand, changedName)
                            }
                        )
                    }
                    is BrandContract.BrandUiState.Loading -> {
                        swipeLoadingCardList()
                    }
                    is BrandContract.BrandUiState.Error -> {}
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
                            onClick = onAddClick
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }

}