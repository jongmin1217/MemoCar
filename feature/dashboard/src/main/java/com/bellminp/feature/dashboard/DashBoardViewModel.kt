package com.bellminp.feature.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bellminp.core.common.base.BaseViewModel
import com.bellminp.core.common.result.Result
import com.bellminp.core.common.result.asResult
import com.bellminp.core.domain.CategoryUseCase
import com.bellminp.core.domain.DashBoardUseCase
import com.bellminp.core.model.data.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val dashBoardUseCase: DashBoardUseCase
) : BaseViewModel<DashBoardContract.Event, DashBoardContract.CategoryUiState, DashBoardContract.Effect>() {

    override fun setInitialState() = DashBoardContract.CategoryUiState.Loading

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
        dashBoardUseCase.getCategoryList().asResult().collect {
            setState {
                when (it) {
                    is Result.Success -> {
                        DashBoardContract.CategoryUiState.Success(
                            category = it.data
                        )
                    }

                    is Result.Loading -> {
                        DashBoardContract.CategoryUiState.Loading
                    }

                    is Result.Error -> {
                        DashBoardContract.CategoryUiState.Error
                    }
                }
            }
        }
    }
}