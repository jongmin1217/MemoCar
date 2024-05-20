package com.bellminp.feature.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bellminp.core.common.result.asResult
import com.bellminp.core.domain.CategoryUseCase
import com.bellminp.core.model.data.Category
import com.bellminp.core.model.data.FollowableCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import com.bellminp.core.common.result.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryUseCase: CategoryUseCase,
) : ViewModel() {

    val categoryUiState: StateFlow<CategoryUiState> = categoryUiState(
        categoryUseCase = categoryUseCase
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = CategoryUiState.Loading,
    )

    fun insertCategory(categoryText : String) = viewModelScope.launch {
        categoryUseCase.insertCategory(categoryText)
    }
}


private fun categoryUiState(
    categoryUseCase: CategoryUseCase,
): Flow<CategoryUiState> {

    val categoryStream: Flow<List<Category>> = categoryUseCase.getCategoryList()

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