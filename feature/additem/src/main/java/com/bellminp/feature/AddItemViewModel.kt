package com.bellminp.feature

import androidx.lifecycle.viewModelScope
import com.bellminp.core.common.base.BaseViewModel
import com.bellminp.core.common.result.Result
import com.bellminp.core.common.result.asResult
import com.bellminp.core.domain.AddItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val addItemUseCase: AddItemUseCase
) : BaseViewModel<AddItemContract.Event,AddItemContract.AddItemUiState,AddItemContract.Effect>(){
    override fun setInitialState() = AddItemContract.AddItemUiState.SUCCESS

    override fun handleEvents(event: AddItemContract.Event){
        when(event){
            is AddItemContract.Event.OnInsert -> {
                viewModelScope.launch{
                    addItemUseCase.insertItem(event.item).run {
                        if(this != -1L) setEffect { AddItemContract.Effect.InsertSuccess }
                        else setEffect { AddItemContract.Effect.InsertError }
                    }
                }
            }
        }
    }
}