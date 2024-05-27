package com.bellminp.feature

import com.bellminp.core.common.base.ViewEvent
import com.bellminp.core.common.base.ViewSideEffect
import com.bellminp.core.common.base.ViewState
import com.bellminp.core.model.data.Item
import com.bellminp.core.model.data.Setting


class AddItemContract {

    sealed class Event : ViewEvent{
        data class OnInsert(val item : Item) : Event()
    }
    sealed class AddItemUiState : ViewState{
        data object SUCCESS : AddItemUiState()
    }

    sealed class Effect : ViewSideEffect{
        data object InsertSuccess : Effect()
        data object InsertError : Effect()
    }
}