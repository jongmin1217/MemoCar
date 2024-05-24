package com.bellminp.memocar.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.bellminp.feature.car.navigation.carScreen
import com.bellminp.feature.dashboard.navigation.DASHBOARD_GRAPH_ROUTE_PATTERN
import com.bellminp.feature.dashboard.navigation.dashboardGraph
import com.bellminp.feature.detail.navigation.detailScreen
import com.bellminp.feature.detail.navigation.navigateToDetail
import com.bellminp.feature.setting.navigation.settingScreen
import com.bellminp.memocar.ui.MemoAppState

@Composable
fun MemoCarNavHost(
    memoAppState: MemoAppState,
    modifier: Modifier = Modifier,
    startDestination: String = DASHBOARD_GRAPH_ROUTE_PATTERN
){
    val navController = memoAppState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ){
        dashboardGraph(
            onItemClick = navController::navigateToDetail,
            nestedGraphs = {
                detailScreen(
                    onBackClick = navController::popBackStack
                )

                carScreen (
                    onBackClick = navController::popBackStack
                )
            }
        )
        settingScreen()
    }
}