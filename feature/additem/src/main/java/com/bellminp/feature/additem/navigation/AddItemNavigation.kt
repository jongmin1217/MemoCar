package com.bellminp.feature.additem.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.bellminp.feature.AddItemRoute

const val ADD_ITEM_ROUTE = "add_item_route"

fun NavController.navigateToAddItem(navOptions: NavOptions? = null) = navigate(ADD_ITEM_ROUTE,navOptions)

fun NavGraphBuilder.addItemScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {

    composable(route = ADD_ITEM_ROUTE) {
        AddItemRoute(
            modifier = modifier,
            onBackClick = onBackClick
        )
    }
}