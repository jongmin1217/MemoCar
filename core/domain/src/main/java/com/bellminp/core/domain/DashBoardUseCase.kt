package com.bellminp.core.domain

import com.bellminp.core.data.repository.DashboardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DashBoardUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository
) {

    fun getCategoryList(type : Int) = dashboardRepository.getPartList(type)

}