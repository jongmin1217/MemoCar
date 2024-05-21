package com.bellminp.memocar.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.bellminp.core.designsystem.component.BaseBottomSheetContent
import com.bellminp.core.designsystem.component.HeaderScreen
import com.bellminp.core.designsystem.component.NavigationBar
import com.bellminp.core.designsystem.component.NavigationBarItem
import com.bellminp.core.designsystem.utils.textSp
import com.bellminp.feature.brand.navigation.BRAND_ROUTE
import com.bellminp.feature.category.navigation.CATEGORY_ROUTE
import com.bellminp.feature.dashboard.navigation.DASHBOARD_ROUTE
import com.bellminp.memocar.R
import com.bellminp.memocar.navigation.MemoCarNavHost
import com.bellminp.memocar.navigation.TopDestination
import com.bellminp.memocar.ui.theme.Purple40

@ExperimentalMaterialApi
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MemoCarApp(
    appState: MemoAppState = rememberMemoCarState()
) {

    val scope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
//
//    val bottomSheetState = rememberModalBottomSheetState(
//        initialValue = ModalBottomSheetValue.Hidden
//    )
//
//    var bottomSheetContent by remember {
//        mutableStateOf<@Composable (()->Unit)>({ Text(text = "null") })
//    }
//
//    LaunchedEffect(key1 = bottomSheetState.isVisible) {
//        if(bottomSheetState.isVisible.not()){
//            focusManager.clearFocus()
//        }
//    }
//
//    ModalBottomSheetLayout(
//        sheetState = bottomSheetState,
//        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
//        sheetBackgroundColor = Color.White,
//        sheetContent = {
//            bottomSheetContent()
//        },
//        modifier = Modifier.imePadding()
//    ) {
//
//    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        contentColor = Color.White,
        containerColor = Color.White,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            if(appState.currentDestination.isShowNavigationBar()){
                BottomBar(
                    destinations = appState.topDestinations,
                    onNavigateToDestination = appState::navigateToTopDestination,
                    currentDestination = appState.currentDestination,
                    modifier = Modifier.testTag("BottomBar")
                )
            }
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .consumeWindowInsets(it)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    ),
                )
        ) {
            val destination = appState.currentTopLevelDestination
            destination?.let { topDestination ->
                HeaderScreen(
                    titleRes = topDestination.titleTextId,
                    actionIcon = if(topDestination.isShowActionBtn) Icons.Rounded.Add else null,
                    actionIconContentDescription = if(topDestination.isShowActionBtn) stringResource(id = R.string.add) else null,
                    onActionClick = { appState.navigateToCar() }
                )
            }

            MemoCarNavHost(
                memoAppState = appState,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}


@Composable
private fun BottomBar(
    destinations: List<TopDestination>,
    onNavigateToDestination: (TopDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopDestinationInHierarchy(destination)
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    Icon(
                        imageVector = destination.unselectedIcon,
                        contentDescription = null,
                    )
                },
                selectedIcon = {
                    Icon(
                        imageVector = destination.selectedIcon,
                        contentDescription = null,
                    )
                },
                label = { Text(stringResource(destination.iconTextId)) }
            )
        }
    }
}

@Composable
fun AddBottomSheet(
    onItemClick: (AddType) -> Unit
) {
    BaseBottomSheetContent {
        val addTypeList = listOf(AddType.CAR,AddType.BRAND,AddType.CATEGORY)
        repeat(3) {
            AddTypeComponent(
                addType = addTypeList[it],
                onClick = onItemClick
            )
        }
    }
}

@Composable
fun AddCategoryComponent(){
    BaseBottomSheetContent {
        var categoryText by remember {
            mutableStateOf("")
        }

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = categoryText,
            onValueChange = {
                categoryText = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 20.dp),
            colors = ButtonColors(
                containerColor = Purple40,
                contentColor = Purple40,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Gray
            ),
            shape = RoundedCornerShape(8.dp),
            enabled = categoryText.isNotBlank()
        ) {
            Text(
                text = stringResource(
                    id = R.string.save
                ),
                style = TextStyle(
                    color = Color.White
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun AddTypeComponent(
    addType: AddType,
    onClick: (AddType) -> Unit
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .clickable { onClick.invoke(addType) }
    ) {
        Text(
            text = stringResource(id = addType.resourceId),
            style = TextStyle(
                fontSize = 18.dp.textSp,
                color = Color.Black,
                fontWeight = FontWeight.W500
            ),
            modifier = Modifier
                .align(Alignment.Center)
        )
    }

}


private fun NavDestination?.isShowNavigationBar() =
    when (this?.route) {
        DASHBOARD_ROUTE -> TopDestination.DASHBOARD
        CATEGORY_ROUTE -> TopDestination.CATEGORY
        BRAND_ROUTE -> TopDestination.BRAND
        else -> null
    }.let {
        it == TopDestination.DASHBOARD || it == TopDestination.CATEGORY || it == TopDestination.BRAND
    }



private fun NavDestination?.isTopDestinationInHierarchy(destination: TopDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false


enum class AddType(
    val resourceId: Int
) {
    CATEGORY(R.string.add_category),
    BRAND(R.string.add_brand),
    CAR(R.string.add_car)
}