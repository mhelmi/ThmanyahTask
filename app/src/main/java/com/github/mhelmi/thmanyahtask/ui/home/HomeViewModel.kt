package com.github.mhelmi.thmanyahtask.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.github.mhelmi.thmanyahtask.data.model.Section
import com.github.mhelmi.thmanyahtask.domain.usecase.GetHomeSectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

private const val PAGE_SIZE = 10

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val getHomeSectionsUseCase: GetHomeSectionsUseCase
) : ViewModel() {

  private val _categories = MutableStateFlow<Set<String>>(emptySet())
  val categories: StateFlow<Set<String>> = _categories

  private val _selectedCategory = MutableStateFlow("")
  val selectedCategory: StateFlow<String> = _selectedCategory

  val homeSections: Flow<PagingData<Section>> = combine(
    getHomeSectionsFlow(),
    _selectedCategory
  ) { pagingData, selectedCategory ->
    if (selectedCategory.isNotEmpty()) {
      pagingData.filter {
        it.contentType == selectedCategory || selectedCategory.isEmpty()
      }
    } else {
      pagingData
    }
  }

  private fun getHomeSectionsFlow(): Flow<PagingData<Section>> = getHomeSectionsUseCase(PAGE_SIZE)
    .updateCategories()
    .cachedIn(viewModelScope)

  private fun Flow<PagingData<Section>>.updateCategories() = onEach { pagingData ->
    val categoriesSet = mutableSetOf<String>()
    pagingData.map {
      categoriesSet.add(it.contentType)
      it
    }
    _categories.value = categoriesSet
  }

  fun selectCategory(category: String) {
    _selectedCategory.value = category
  }
}