package com.bellminp.feature.car.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.bellminp.feature.car.CarRoute


const val CAR_ROUTE = "car_route"

fun NavController.navigateToCar(navOptions: NavOptions? = null) = navigate(CAR_ROUTE,navOptions)

fun NavGraphBuilder.carScreen(
    onBackClick: () -> Unit
) {

    composable(route = CAR_ROUTE) {
        CarRoute(onBackClick = onBackClick)
    }
}