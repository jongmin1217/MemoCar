package com.bellminp.core.domain

import com.bellminp.core.data.repository.DashboardRepository
import com.bellminp.core.model.data.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DashBoardUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository
) {

    fun getCategoryList() : Flow<List<Category>> = dashboardRepository.getCategoryList()

}