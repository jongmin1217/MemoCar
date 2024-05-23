package com.bellminp.feature.setting

import com.bellminp.core.common.base.ViewEvent
import com.bellminp.core.common.base.ViewSideEffect
import com.bellminp.core.common.base.ViewState
import com.bellminp.core.model.data.CarInfoType
import com.bellminp.core.model.data.Category
import com.bellminp.core.model.data.Setting

class SettingContract {
    sealed class Event : ViewEvent {
        data class OnDeleteSetting(val setting : Setting) : Event()
        data class OnUpdateNameSetting(val setting: Setting, val changedName : String) : Event()
        data class OnInsertSetting(val carInfoType : CarInfoType) : Event()
    }

    sealed class SettingUiState : ViewState {
        data class Success(val setting: List<Setting>) : SettingUiState()
        data object Error : SettingUiState()
        data object Loading : SettingUiState()
    }

    sealed class Effect : ViewSideEffect
}