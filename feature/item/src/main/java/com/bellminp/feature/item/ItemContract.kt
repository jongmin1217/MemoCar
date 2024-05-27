package com.bellminp.feature.item

import com.bellminp.core.common.base.ViewEvent
import com.bellminp.core.common.base.ViewSideEffect
import com.bellminp.core.common.base.ViewState
import com.bellminp.core.model.data.Item

class ItemContract {
    sealed class Event : ViewEvent
    sealed class ItemUiState : ViewState {
        data class Success(val itemList : List<Item>) : ItemUiState()
        data object Loading : ItemUiState()
        data object Error : ItemUiState()
    }

    sealed class Effect : ViewSideEffect
}