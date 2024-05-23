package com.bellminp.feature.category

import com.bellminp.core.common.base.ViewEvent
import com.bellminp.core.common.base.ViewSideEffect
import com.bellminp.core.common.base.ViewState
import com.bellminp.core.model.data.Category

class CategoryContract {
    sealed class Event : ViewEvent {
        data class OnDeleteCategory(val category : Category) : Event()
        data class OnUpdateNameCategory(val category: Category, val changedName : String) : Event()
        data class OnMoveCategory(val categoryList : List<Category>) : Event()
        data object OnInsertCategory : Event()
    }

    sealed class CategoryUiState : ViewState {
        data class Success(val category: List<Category>) : CategoryUiState()
        data object Error : CategoryUiState()
        data object Loading : CategoryUiState()
    }

    sealed class Effect : ViewSideEffect

}