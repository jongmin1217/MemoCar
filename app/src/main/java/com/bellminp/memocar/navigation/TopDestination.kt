package com.bellminp.memocar.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.bellminp.feature.dashboard.R as dashboardR
import com.bellminp.feature.setting.R as settingR

enum class TopDestination(
    val titleTextId: Int,
    val iconTextId : Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val isShowActionBtn : Boolean
) {
    DASHBOARD(
        titleTextId = dashboardR.string.feature_dashboard,
        iconTextId = dashboardR.string.feature_dashboard,
        selectedIcon = Icons.Rounded.Home,
        unselectedIcon = Icons.Outlined.Home,
        isShowActionBtn = true
    ),
    SETTING(
        titleTextId = settingR.string.setting,
        iconTextId = settingR.string.setting,
        selectedIcon = Icons.Rounded.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        isShowActionBtn = false
    )
}