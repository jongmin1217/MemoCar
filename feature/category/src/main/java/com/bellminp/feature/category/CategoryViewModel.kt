package com.bellminp.feature.category

import androidx.lifecycle.viewModelScope
import com.bellminp.core.common.base.BaseViewModel
import com.bellminp.core.common.result.Result
import com.bellminp.core.common.result.asResult
import com.bellminp.core.domain.CategoryUseCase
import com.bellminp.core.model.data.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryUseCase: CategoryUseCase,
) : BaseViewModel<CategoryContract.Event, CategoryContract.CategoryUiState, CategoryContract.Effect>() {


    override fun setInitialState() = CategoryContract.CategoryUiState.Loading

    override fun handleEvents(event: CategoryContract.Event) {
        when (event) {
            is CategoryContract.Event.OnInsertCategory -> insertCategory()
            is CategoryContract.Event.OnDeleteCategory -> deleteCategory(event.category)
            is CategoryContract.Event.OnMoveCategory -> moveCategory(event.categoryList)
            is CategoryContract.Event.OnUpdateCategory -> updateCategory(event.category)
        }
    }

    init {
        getCategoryList()
    }

    private fun getCategoryList() = viewModelScope.launch {
        categoryUseCase.getCategoryList().asResult().collect {
            setState {
                when (it) {
                    is Result.Success -> {
                        CategoryContract.CategoryUiState.Success(
                            category = it.data
                        )
                    }

                    is Result.Loading -> {
                        CategoryContract.CategoryUiState.Loading
                    }

                    is Result.Error -> {
                        CategoryContract.CategoryUiState.Error
                    }
                }
            }
        }
    }

    private fun insertCategory() = viewModelScope.launch {
        categoryUseCase.insertCategory()
    }

    private fun deleteCategory(category : Category) = viewModelScope.launch {
        categoryUseCase.deleteCategory(category.id)
    }

    private fun updateCategory(category: Category) = viewModelScope.launch {
        categoryUseCase.updateCategory(category)
    }

    private fun moveCategory(categoryList: List<Category>) = viewModelScope.launch {
        categoryUseCase.upsertCategory(categoryList)
    }


}
