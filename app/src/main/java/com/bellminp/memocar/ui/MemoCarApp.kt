package com.bellminp.memocar.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.bellminp.core.ui.textSp
import com.bellminp.feature.brand.navigation.BRAND_ROUTE
import com.bellminp.feature.car.navigation.CAR_ROUTE
import com.bellminp.feature.category.navigation.CATEGORY_ROUTE
import com.bellminp.feature.dashboard.navigation.DASHBOARD_GRAPH_ROUTE_PATTERN
import com.bellminp.feature.dashboard.navigation.DASHBOARD_ROUTE
import com.bellminp.memocar.navigation.Destination
import com.bellminp.memocar.navigation.MemoCarNavHost
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MemoCarApp(
    appState: MemoAppState = rememberMemoCarState()
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val scope = rememberCoroutineScope()

        val bottomSheetState = rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden
        )

        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            sheetBackgroundColor = Color.White,
            sheetContent = {
                AddBottomSheet {
                    scope.launch {
                        bottomSheetState.hide()
                        when (it) {
                            AddType.CAR -> appState.navigateToCar()
                            AddType.CATEGORY -> appState.navigateToCategory()
                            AddType.BRAND -> appState.navigateToBrand()
                        }
                    }
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                val navDestination = appState.currentDestination
                HeaderScreen(
                    navDestination = navDestination,
                    onBackCLick = { appState.navigateBackStack() },
                    onAddClick = {
                        scope.launch {
                            bottomSheetState.show()
                        }
                    }
                )

                MemoCarNavHost(
                    memoAppState = appState,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderScreen(
    modifier: Modifier = Modifier,
    navDestination: NavDestination?,
    onBackCLick: () -> Unit,
    onAddClick: () -> Unit
) {
    navDestination.currentDestination()?.let {

        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(id = it.titleTextId),
                    modifier = Modifier.animateContentSize()
                )
            },
            navigationIcon = {
                AnimatedVisibility(
                    visible = it.isShowBack,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    IconButton(onClick = onBackCLick) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = "close",
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
            },
            actions = {
                AnimatedVisibility(
                    visible = it.isShowAdd,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    IconButton(onClick = onAddClick) {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = "add",
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
            modifier = modifier
                .height(60.dp)
                .testTag("HeaderScreen"),
        )
    }
}

@Composable
fun AddBottomSheet(
    onItemClick: (AddType) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Divider(
                modifier = Modifier
                    .width(36.dp)
                    .height(5.dp)
                    .clip(RoundedCornerShape(4.5.dp))
                    .align(Alignment.CenterHorizontally),
                color = com.bellminp.memocar.ui.theme.Divider
            )
            Spacer(modifier = Modifier.height(10.dp))

            val addTypeList = listOf(AddType.CATEGORY, AddType.BRAND, AddType.CAR)
            repeat(3) {
                AddTypeComponent(
                    addType = addTypeList[it],
                    onClick = onItemClick
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun AddTypeComponent(
    addType: AddType,
    onClick: (AddType) -> Unit
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .clickable { onClick.invoke(addType) }
    ) {
        Text(
            text = addType.text,
            style = TextStyle(
                fontSize = 18.dp.textSp,
                color = Color.Black,
                fontWeight = FontWeight.W700
            ),
            modifier = Modifier
                .align(Alignment.Center)
        )
    }

}


private fun NavDestination?.currentDestination() =
    when (this?.route) {
        DASHBOARD_ROUTE -> Destination.DASHBOARD
        CAR_ROUTE -> Destination.CAR
        CATEGORY_ROUTE -> Destination.CATEGORY
        BRAND_ROUTE -> Destination.BRAND
        else -> null
    }

enum class AddType(
    val text: String
) {
    CATEGORY("카테고리 관리"),
    BRAND("브랜드 관리"),
    CAR("차량 추가")
}