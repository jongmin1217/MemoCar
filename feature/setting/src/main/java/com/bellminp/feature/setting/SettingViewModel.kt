package com.bellminp.feature.setting

import androidx.lifecycle.viewModelScope
import com.bellminp.core.common.base.BaseViewModel
import com.bellminp.core.common.result.Result
import com.bellminp.core.common.result.asResult
import com.bellminp.core.domain.SettingUseCase
import com.bellminp.core.model.data.Brand
import com.bellminp.core.model.data.CarInfoType
import com.bellminp.core.model.data.Setting
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val settingUseCase: SettingUseCase
) : BaseViewModel<SettingContract.Event,SettingContract.SettingUiState,SettingContract.Effect>() {
    override fun setInitialState() = SettingContract.SettingUiState.Loading

    override fun handleEvents(event: SettingContract.Event) {
        when(event){
            is SettingContract.Event.OnInsertSetting -> insertSetting(event.carInfoType)
            is SettingContract.Event.OnDeleteSetting -> deleteSetting(event.setting)
            is SettingContract.Event.OnUpdateNameSetting -> updateNameSetting(event.setting, event.changedName)
            is SettingContract.Event.OnChangeTab -> getSettingList(event.id)
        }
    }

    private var searchJob : Job? = null

    init {
        getSettingList(0)
    }

    private fun getSettingList(id : Long) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch{
            settingUseCase.getSettingList(id).asResult().collect {
                setState {
                    when (it) {
                        is Result.Success -> {
                            SettingContract.SettingUiState.Success(
                                setting = it.data
                            )
                        }

                        is Result.Loading -> {
                            SettingContract.SettingUiState.Loading
                        }

                        is Result.Error -> {
                            SettingContract.SettingUiState.Error
                        }
                    }
                }
            }
        }
    }

    private fun insertSetting(carInfoType : CarInfoType) = viewModelScope.launch {
        settingUseCase.insertSetting(carInfoType.id)
    }

    private fun deleteSetting(setting : Setting) = viewModelScope.launch {
        settingUseCase.deleteSetting(setting.id)
    }

    private fun updateNameSetting(setting: Setting, changedName : String) = viewModelScope.launch {
        settingUseCase.updateSetting(setting.copy(name = changedName))
    }
}