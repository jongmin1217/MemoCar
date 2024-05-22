package com.bellminp.feature.brand

import androidx.lifecycle.viewModelScope
import com.bellminp.core.common.base.BaseViewModel
import com.bellminp.core.common.result.Result
import com.bellminp.core.common.result.asResult
import com.bellminp.core.domain.BrandUseCase
import com.bellminp.core.model.data.Brand
import com.bellminp.core.model.data.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrandViewModel @Inject constructor(
    private val brandUseCase: BrandUseCase
) : BaseViewModel<BrandContract.Event, BrandContract.BrandUiState, BrandContract.Effect>() {
    override fun setInitialState() = BrandContract.BrandUiState.Loading

    override fun handleEvents(event: BrandContract.Event) {
        when (event) {
            is BrandContract.Event.OnInsertBrand -> insertBrand()
            is BrandContract.Event.OnDeleteBrand -> deleteBrand(event.brand)
            is BrandContract.Event.OnUpdateBrand -> updateBrand(event.brand)
        }
    }
    
    init {
        getBrandList()
    }

    private fun getBrandList() = viewModelScope.launch {
        brandUseCase.getBrandList().asResult().collect {
            setState {
                when (it) {
                    is Result.Success -> {
                        BrandContract.BrandUiState.Success(
                            brand = it.data
                        )
                    }

                    is Result.Loading -> {
                        BrandContract.BrandUiState.Loading
                    }

                    is Result.Error -> {
                        BrandContract.BrandUiState.Error
                    }
                }
            }
        }
    }

    private fun insertBrand() = viewModelScope.launch {
        brandUseCase.insertBrand()
    }

    private fun deleteBrand(brand : Brand) = viewModelScope.launch {
        brandUseCase.deleteBrand(brand.id)
    }

    private fun updateBrand(brand: Brand) = viewModelScope.launch {
        brandUseCase.updateBrand(brand)
    }
}