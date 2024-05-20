package com.bellminp.feature.brand.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.bellminp.feature.brand.BrandRoute

const val BRAND_ROUTE = "brand_route"

fun NavController.navigateToBrand(navOptions: NavOptions? = null) = navigate(BRAND_ROUTE,navOptions)

fun NavGraphBuilder.brandScreen() {
    composable(route = BRAND_ROUTE) {
        BrandRoute()
    }
}