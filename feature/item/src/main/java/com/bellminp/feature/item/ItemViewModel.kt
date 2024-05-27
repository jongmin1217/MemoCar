package com.bellminp.feature.item

import androidx.lifecycle.viewModelScope
import com.bellminp.core.common.base.BaseViewModel
import com.bellminp.core.common.result.Result
import com.bellminp.core.common.result.asResult
import com.bellminp.core.domain.ItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val itemUseCase: ItemUseCase
) : BaseViewModel<ItemContract.Event,ItemContract.ItemUiState,ItemContract.Effect>(){
    override fun setInitialState() = ItemContract.ItemUiState.Loading

    override fun handleEvents(event: ItemContract.Event) {

    }

    init {
        getItemUi()
    }

    private fun getItemUi() = viewModelScope.launch{
        itemUseCase.getItemList().asResult().collect{
            setState {
                when (it) {
                    is Result.Success -> {
                        ItemContract.ItemUiState.Success(
                            itemList = it.data
                        )
                    }

                    is Result.Loading -> {
                        ItemContract.ItemUiState.Loading
                    }

                    is Result.Error -> {
                        ItemContract.ItemUiState.Error
                    }
                }
            }
        }
    }

}