package com.bellminp.feature.setting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.bellminp.feature.setting.SettingRoute


const val SETTING_ROUTE = "setting_route"

fun NavController.navigateToSetting(navOptions: NavOptions? = null) = navigate(SETTING_ROUTE,navOptions)

fun NavGraphBuilder.settingScreen(
    onBackClick : () -> Unit
) {

    composable(route = SETTING_ROUTE) {
        SettingRoute(onBackClick = onBackClick)
    }
}