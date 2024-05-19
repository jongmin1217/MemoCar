package com.bellminp.memocar.navigation

import com.bellminp.feature.dashboard.R as dashboardR
import com.bellminp.feature.car.R as carR
import com.bellminp.feature.brand.R as brandR
import com.bellminp.feature.category.R as categoryR

enum class Destination(
    val titleTextId: Int,
    val isShowBack: Boolean,
    val isShowAdd: Boolean
) {
    DASHBOARD(
        titleTextId = dashboardR.string.feature_dashboard,
        isShowAdd = true,
        isShowBack = false
    ),
    CAR(
        titleTextId = carR.string.feature_car,
        isShowAdd = false,
        isShowBack = true
    ),
    BRAND(
        titleTextId = brandR.string.feature_brand,
        isShowAdd = false,
        isShowBack = true
    ),
    CATEGORY(
        titleTextId = categoryR.string.feature_category,
        isShowAdd = false,
        isShowBack = true
    )
}