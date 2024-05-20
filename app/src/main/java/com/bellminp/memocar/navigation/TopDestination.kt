package com.bellminp.memocar.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.bellminp.feature.dashboard.R as dashboardR
import com.bellminp.feature.brand.R as brandR
import com.bellminp.feature.category.R as categoryR

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
    BRAND(
        titleTextId = brandR.string.feature_brand,
        iconTextId = brandR.string.feature_brand,
        selectedIcon = Icons.Rounded.Build,
        unselectedIcon = Icons.Outlined.Build,
        isShowActionBtn = false
    ),
    CATEGORY(
        titleTextId = categoryR.string.feature_category,
        iconTextId = categoryR.string.feature_category,
        selectedIcon = Icons.Rounded.Create,
        unselectedIcon = Icons.Outlined.Create,
        isShowActionBtn = false
    )
}