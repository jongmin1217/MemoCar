package com.bellminp.feature.setting

import androidx.lifecycle.viewModelScope
import com.bellminp.core.common.base.BaseViewModel
import com.bellminp.core.common.result.Result
import com.bellminp.core.common.result.asResult
import com.bellminp.core.domain.SettingUseCase
import com.bellminp.core.model.data.Setting
import com.bellminp.core.model.data.SettingPagerData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.bellminp.core.model.data.SelectStateType.*

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val settingUseCase: SettingUseCase
) : BaseViewModel<SettingContract.Event,SettingContract.SettingUiState,SettingContract.Effect>() {
    override fun setInitialState() = SettingContract.SettingUiState.Loading

    override fun handleEvents(event: SettingContract.Event) {
        when(event){
            is SettingContract.Event.OnInsertSetting -> insertSetting(event.insertId)
            is SettingContract.Event.OnDeleteSetting -> deleteSetting(event.setting)
            is SettingContract.Event.OnUpdateNameSetting -> updateNameSetting(event.setting, event.changedName)
            is SettingContract.Event.OnMoveSetting -> upsertSetting(event.settingList)
        }
    }


    init {
        getSettingList()

    }

    private fun getSettingList() = viewModelScope.launch{
        settingUseCase.getAllSettingList().asResult().collect {
            setState {
                when (it) {
                    is Result.Success -> {
                        SettingContract.SettingUiState.Success(
                            settingList = listOf(
                                SettingPagerData(CATEGORY,it.data.filter { it.type == CATEGORY.id }),
                                SettingPagerData(BRAND,it.data.filter { it.type == BRAND.id }),
                                SettingPagerData(ENGINE,it.data.filter { it.type == ENGINE.id }),
                                SettingPagerData(SUPERCHARGING,it.data.filter { it.type == SUPERCHARGING.id }),
                                SettingPagerData(ENGINE_POSITION,it.data.filter { it.type == ENGINE_POSITION.id }),
                                SettingPagerData(DRIVE_METHOD,it.data.filter { it.type == DRIVE_METHOD.id }),
                                SettingPagerData(TRANSMISSION,it.data.filter { it.type == TRANSMISSION.id }),
                                SettingPagerData(DESIGN,it.data.filter { it.type == DESIGN.id })
                            )
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

    private fun insertSetting(settingType : Int) = viewModelScope.launch {
        settingUseCase.insertSetting(settingType)
    }

    private fun deleteSetting(setting : Setting) = viewModelScope.launch {
        settingUseCase.deleteSetting(setting.id)
    }

    private fun updateNameSetting(setting: Setting, changedName : String) = viewModelScope.launch {
        settingUseCase.updateSetting(setting.copy(name = changedName))
    }

    private fun upsertSetting(settingList : List<Setting>) = viewModelScope.launch {
        settingUseCase.upsertSetting(settingList)
    }
}