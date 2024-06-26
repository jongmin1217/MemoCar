package com.bellminp.feature.car

import com.bellminp.core.common.base.ViewEvent
import com.bellminp.core.common.base.ViewSideEffect
import com.bellminp.core.common.base.ViewState
import com.bellminp.core.model.data.Setting

class CarContract {

    sealed class Event : ViewEvent
    sealed class CarUiState : ViewState {
        data class Success(
            val setting : List<Setting>
        ) : CarUiState()
        data object Error : CarUiState()
        data object Loading : CarUiState()
    }

    sealed class Effect : ViewSideEffect
}