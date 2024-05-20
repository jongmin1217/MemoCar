package com.bellminp.feature.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.bellminp.feature.detail.DetailRoute


const val DETAIL_ROUTE = "detail_route"

fun NavController.navigateToDetail(navOptions: NavOptions? = null) = navigate(DETAIL_ROUTE,navOptions)

fun NavGraphBuilder.detailScreen(
    onBackClick : () -> Unit
) {

    composable(route = DETAIL_ROUTE) {
        DetailRoute()
    }
}