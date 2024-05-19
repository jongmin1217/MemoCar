package com.bellminp.feature.category.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.bellminp.feature.category.CategoryRoute


const val CATEGORY_ROUTE = "category_route"

fun NavController.navigateToCategory(navOptions: NavOptions? = null) = navigate(CATEGORY_ROUTE,navOptions)

fun NavGraphBuilder.categoryScreen(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit
) {

    composable(route = CATEGORY_ROUTE) {
        CategoryRoute()
    }
}