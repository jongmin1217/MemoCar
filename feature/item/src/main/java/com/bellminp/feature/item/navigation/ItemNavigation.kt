package com.bellminp.feature.item.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bellminp.feature.item.ItemRoute


const val ITEM_GRAPH_ROUTE_PATTERN = "item_graph"
const val ITEM_ROUTE = "item_route"
fun NavController.navigationToItemGraph(navOptions: NavOptions? = null){
    this.navigate(ITEM_GRAPH_ROUTE_PATTERN, navOptions)
}


fun NavGraphBuilder.itemGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = ITEM_GRAPH_ROUTE_PATTERN,
        startDestination = ITEM_ROUTE,
    ){
        composable(route = ITEM_ROUTE) {
            ItemRoute()
        }
        nestedGraphs()
    }
}
