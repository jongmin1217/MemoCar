package com.bellminp.memocar.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.bellminp.feature.brand.navigation.brandScreen
import com.bellminp.feature.brand.navigation.navigateToBrand
import com.bellminp.feature.car.navigation.carScreen
import com.bellminp.feature.car.navigation.navigateToCar
import com.bellminp.feature.category.navigation.categoryScreen
import com.bellminp.feature.dashboard.navigation.DASHBOARD_GRAPH_ROUTE_PATTERN
import com.bellminp.feature.dashboard.navigation.DASHBOARD_ROUTE
import com.bellminp.feature.dashboard.navigation.dashboardGraph
import com.bellminp.feature.detail.navigation.detailScreen
import com.bellminp.feature.detail.navigation.navigateToDetail
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
            }
        )
        brandScreen()
        categoryScreen()
        carScreen(
            onBackClick = navController::popBackStack
        )
    }
}