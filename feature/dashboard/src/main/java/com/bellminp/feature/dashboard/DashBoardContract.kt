package com.bellminp.feature.dashboard

import com.bellminp.core.common.base.ViewEvent
import com.bellminp.core.common.base.ViewSideEffect
import com.bellminp.core.common.base.ViewState
import com.bellminp.core.model.data.Category

class DashBoardContract {
    sealed class Event : ViewEvent{
        data class OnSelectedId(val id : Long?) : Event()
    }

    sealed class CategoryUiState : ViewState {
        data class Success(val category: List<Category>) : CategoryUiState()
        data object Error : CategoryUiState()
        data object Loading : CategoryUiState()
    }

    sealed class Effect : ViewSideEffect
}