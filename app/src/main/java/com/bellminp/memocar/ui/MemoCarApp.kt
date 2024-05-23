package com.bellminp.memocar.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
    appState: MemoAppState = rememberMemoCarState(),
) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        contentColor = Color.White,
        containerColor = Color.White,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            BottomBar(
                destinations = appState.topDestinations,
                onNavigateToDestination = appState::navigateToTopDestination,
                currentDestination = appState.currentDestination,
                modifier = Modifier.testTag("BottomBar")
            )
        }
    ) {

        Box(
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

            MemoCarNavHost(
                memoAppState = appState,
                modifier = Modifier.fillMaxSize()
            )

            val destination = appState.currentTopLevelDestination
            destination?.let { topDestination ->
                HeaderScreen(
                    modifier = Modifier
                        .align(Alignment.TopCenter),
                    titleRes = topDestination.titleTextId,
                    actionContent = {
                        if (topDestination.isShowActionBtn) {
                            Row {
                                IconButton(
                                    onClick = { appState.navigateToSetting() }
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Settings,
                                        contentDescription = "setting",
                                        tint = MaterialTheme.colorScheme.onSurface,
                                    )
                                }
                                IconButton(
                                    onClick = { appState.navigateToCar() }
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Add,
                                        contentDescription = "add",
                                        tint = MaterialTheme.colorScheme.onSurface,
                                    )
                                }
                            }
                        }
                    }
                )
            }
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



private fun NavDestination?.isTopDestinationInHierarchy(destination: TopDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
