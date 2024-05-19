package com.bellminp.feature.dashboard.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bellminp.feature.dashboard.DashBoardRoute

const val DASHBOARD_GRAPH_ROUTE_PATTERN = "dashboard_graph"
const val DASHBOARD_ROUTE = "dashboard_route"

fun NavController.navigationToDashboardGraph(navOptions: NavOptions? = null) =
    navigate(
        DASHBOARD_GRAPH_ROUTE_PATTERN,
        navOptions
    )



fun NavGraphBuilder.dashboardGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = DASHBOARD_GRAPH_ROUTE_PATTERN,
        startDestination = DASHBOARD_ROUTE,
    ) {
        composable(route = DASHBOARD_ROUTE) {
            DashBoardRoute()
        }
        nestedGraphs()
    }
}
