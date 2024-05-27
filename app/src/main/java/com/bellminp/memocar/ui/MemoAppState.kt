package com.bellminp.memocar.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.util.trace
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.bellminp.core.ui.TrackDisposableJank
import com.bellminp.feature.additem.navigation.navigateToAddItem
import com.bellminp.feature.car.navigation.navigateToCar
import com.bellminp.feature.dashboard.navigation.DASHBOARD_ROUTE
import com.bellminp.feature.dashboard.navigation.navigationToDashboardGraph
import com.bellminp.feature.item.navigation.ITEM_ROUTE
import com.bellminp.feature.item.navigation.navigationToItemGraph
import com.bellminp.feature.setting.navigation.SETTING_ROUTE
import com.bellminp.feature.setting.navigation.navigateToSetting
import com.bellminp.memocar.navigation.TopDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ticker

@Composable
fun rememberMemoCarState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
) : MemoAppState{
    NavigationTrackingSideEffect(navController)
    return remember(
        navController,
        coroutineScope
    ) {
        MemoAppState(
            navController,
            coroutineScope
        )
    }
}

@Stable
class MemoAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope
){

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopDestination?
        @Composable get() = when (currentDestination?.route) {
            DASHBOARD_ROUTE -> TopDestination.DASHBOARD
            SETTING_ROUTE -> TopDestination.SETTING
            ITEM_ROUTE -> TopDestination.ITEM
            else -> null
        }

    val topDestinations : List<TopDestination> = TopDestination.entries

    fun navigateToTopDestination(topDestination: TopDestination) {
        trace("Navigation: ${topDestination.name}") {
            val topNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }

            when (topDestination) {
                TopDestination.DASHBOARD -> navController.navigationToDashboardGraph(topNavOptions)
                TopDestination.SETTING -> navController.navigateToSetting(topNavOptions)
                TopDestination.ITEM -> navController.navigationToItemGraph(topNavOptions)
            }
        }
    }
    fun navigateToCar() = navController.navigateToCar()
    fun navigateToAddItem() = navController.navigateToAddItem()

}


@Composable
private fun NavigationTrackingSideEffect(navController: NavHostController) {
    TrackDisposableJank(navController) { metricsHolder ->
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            metricsHolder.state?.putState("Navigation", destination.route.toString())
        }

        navController.addOnDestinationChangedListener(listener)

        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
}
