package com.bellminp.feature.dashboard

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.bellminp.core.common.base.BaseViewModel
import com.bellminp.core.common.result.Result
import com.bellminp.core.common.result.asResult
import com.bellminp.core.domain.DashBoardUseCase
import com.bellminp.core.model.data.SelectStateType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val dashBoardUseCase: DashBoardUseCase
) : BaseViewModel<DashBoardContract.Event, DashBoardContract.DashBoardUiState, DashBoardContract.Effect>() {

    override fun setInitialState() = DashBoardContract.DashBoardUiState.Loading

    override fun handleEvents(event: DashBoardContract.Event) {
        when(event){
            is DashBoardContract.Event.OnSelectedId -> {
                Log.d("qweqwe",event.id.toString())
            }
        }
    }

    init {
        getCategoryList()
    }

    private fun getCategoryList() = viewModelScope.launch {
        dashBoardUseCase.getCategoryList(SelectStateType.CATEGORY.id).asResult().collect {
            setState {
                when (it) {
                    is Result.Success -> {
                        DashBoardContract.DashBoardUiState.Success(
                            setting = it.data
                        )
                    }

                    is Result.Loading -> {
                        DashBoardContract.DashBoardUiState.Loading
                    }

                    is Result.Error -> {
                        DashBoardContract.DashBoardUiState.Error
                    }
                }
            }
        }
    }
}