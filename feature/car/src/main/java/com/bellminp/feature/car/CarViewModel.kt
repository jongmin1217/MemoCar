package com.bellminp.feature.car

import androidx.lifecycle.viewModelScope
import com.bellminp.core.common.base.BaseViewModel
import com.bellminp.core.common.result.Result
import com.bellminp.core.common.result.asResult
import com.bellminp.core.domain.CarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(
    private val carUseCase: CarUseCase,
) : BaseViewModel<CarContract.Event, CarContract.CarUiState, CarContract.Effect>() {
    override fun setInitialState() = CarContract.CarUiState.Loading
    override fun handleEvents(event: CarContract.Event) {

    }
    init {
        getCarUi()
    }
    private fun getCarUi() = viewModelScope.launch {
        val categoryStream = carUseCase.getCategoryList()
        val brandStream = carUseCase.getBrandList()
        val settingStream = carUseCase.getAllSettingList()

        combine(
            categoryStream,
            brandStream,
            settingStream,
            ::Triple
        ).asResult().collect {
            setState {
                when (it) {
                    is Result.Success -> {
                        val (category, brand, setting) = it.data
                        CarContract.CarUiState.Success(
                            category = category,
                            brand = brand,
                            setting = setting
                        )
                    }

                    is Result.Loading -> {
                        CarContract.CarUiState.Loading
                    }

                    is Result.Error -> {
                        CarContract.CarUiState.Error
                    }
                }
            }
        }
    }

}