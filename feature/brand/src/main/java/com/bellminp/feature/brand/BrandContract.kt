package com.bellminp.feature.brand

import com.bellminp.core.common.base.ViewEvent
import com.bellminp.core.common.base.ViewSideEffect
import com.bellminp.core.common.base.ViewState
import com.bellminp.core.model.data.Brand
import com.bellminp.core.model.data.Category

class BrandContract {

    sealed class Event : ViewEvent {
        data class OnDeleteBrand(val brand : Brand) : Event()
        data class OnUpdateNameBrand(val brand: Brand, val changedName : String) : Event()
        data class OnUpdateImageBrand(val brand: Brand, val imageUrl : String) : Event()
        data object OnInsertBrand : Event()
    }

    sealed class BrandUiState : ViewState {
        data class Success(val brand: List<Brand>) : BrandUiState()
        data object Error : BrandUiState()
        data object Loading : BrandUiState()
    }

    sealed class Effect : ViewSideEffect
}