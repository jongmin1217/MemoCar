package com.bellminp.feature.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val dashBoardUseCase: DashBoardUseCase
) : ViewModel() {

    val categoryUiState: StateFlow<CategoryUiState> = categoryUiState(
        dashBoardUseCase = dashBoardUseCase
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = CategoryUiState.Loading,
    )

}


private fun categoryUiState(
    dashBoardUseCase: DashBoardUseCase,
): Flow<CategoryUiState> {

    val categoryStream: Flow<List<Category>> = dashBoardUseCase.getCategoryList()

    return categoryStream.asResult().map { followedCategoryToCategoryResult ->
        when (followedCategoryToCategoryResult) {
            is Result.Success -> {
                CategoryUiState.Success(
                    category = followedCategoryToCategoryResult.data
                )
            }

            is Result.Loading -> {
                CategoryUiState.Loading
            }

            is Result.Error -> {
                CategoryUiState.Error
            }
        }
    }
}


sealed interface CategoryUiState {
    data class Success(val category: List<Category>) : CategoryUiState
    data object Error : CategoryUiState
    data object Loading : CategoryUiState
}