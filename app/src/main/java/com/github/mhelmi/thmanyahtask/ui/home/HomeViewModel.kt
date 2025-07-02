package com.github.mhelmi.thmanyahtask.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.github.mhelmi.thmanyahtask.data.model.Section
import com.github.mhelmi.thmanyahtask.domain.usecase.GetHomeSectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val PAGE_SIZE = 10

@HiltViewModel
class HomeViewModel @Inject constructor(
  getHomeSectionsUseCase: GetHomeSectionsUseCase
) : ViewModel() {

  val homeSections: Flow<PagingData<Section>> = getHomeSectionsUseCase(PAGE_SIZE)
    .cachedIn(viewModelScope)

}