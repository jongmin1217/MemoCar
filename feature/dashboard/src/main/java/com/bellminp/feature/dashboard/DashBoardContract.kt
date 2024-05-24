package com.bellminp.feature.dashboard

import com.bellminp.core.common.base.ViewEvent
import com.bellminp.core.common.base.ViewSideEffect
import com.bellminp.core.common.base.ViewState
import com.bellminp.core.model.data.Setting

class DashBoardContract {
    sealed class Event : ViewEvent{
        data class OnSelectedId(val id : Long?) : Event()
    }

    sealed class DashBoardUiState : ViewState {
        data class Success(val setting: List<Setting>) : DashBoardUiState()
        data object Error : DashBoardUiState()
        data object Loading : DashBoardUiState()
    }

    sealed class Effect : ViewSideEffect
}