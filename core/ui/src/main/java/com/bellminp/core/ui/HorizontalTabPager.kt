package com.bellminp.core.ui


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bellminp.core.designsystem.component.AddBtn
import com.bellminp.core.designsystem.component.Tab
import com.bellminp.core.model.data.Setting
import com.bellminp.core.model.data.SettingPagerData

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalTabPager(
    modifier: Modifier = Modifier,
    pagerState : PagerState,
    itemList : List<SettingPagerData>,
    isKeyboardOpen : Boolean,
    onTabIndexChange: (Int) -> Unit,
    onAddClick : ((Int) -> Unit) = {},
    onDeleteClick : ((Setting) -> Unit) = {},
    onChangeName : ((Setting, String) -> Unit) = { _, _ -> },
    onUpsert : ((List<Setting>) -> Unit)= {}
){

    Column(
        modifier = modifier
    ) {
        Tab(
            tabIndex = pagerState.currentPage,
            tabItemList = itemList.map { it.type.title },
            onTabIndexChange = onTabIndexChange
        )

        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState
        ) { index ->

            LazyColumn(
                state = LazyListState(),
                modifier = Modifier
                    .fillMaxSize()
            ) {

                swipeCardList(
                    items = itemList[index].list,
                    isKeyboardOpen = isKeyboardOpen,
                    onDeleteClick = { onDeleteClick(it) },
                    onNameChange = { setting, changedName ->
                        onChangeName(setting, changedName)
                    }
                )

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
                            onClick = { onAddClick(itemList[index].type.id) }
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }
}


