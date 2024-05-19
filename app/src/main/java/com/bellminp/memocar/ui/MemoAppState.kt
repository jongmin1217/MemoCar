package com.bellminp.memocar.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bellminp.core.ui.TrackDisposableJank
import com.bellminp.feature.brand.navigation.navigateToBrand
import com.bellminp.feature.car.navigation.navigateToCar
import com.bellminp.feature.category.navigation.navigateToCategory
import com.bellminp.feature.dashboard.navigation.DASHBOARD_ROUTE
import com.bellminp.memocar.navigation.Destination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

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

    fun navigateToCar() = navController.navigateToCar()
    fun navigateToCategory() = navController.navigateToCategory()
    fun navigateToBrand() = navController.navigateToBrand()
    fun navigateBackStack() = navController.popBackStack()

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
