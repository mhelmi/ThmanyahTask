package com.github.mhelmi.thmanyahtask.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.github.mhelmi.thmanyahtask.data.model.Section
import com.github.mhelmi.thmanyahtask.di.IoDispatcher
import com.github.mhelmi.thmanyahtask.domain.usecase.GetHomeSectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
  getHomeSectionsUseCase: GetHomeSectionsUseCase,
  @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

  companion object {
    const val PAGE_SIZE = 10
  }

  val homeSections: Flow<PagingData<Section>> = getHomeSectionsUseCase(PAGE_SIZE)
    .flowOn(ioDispatcher)
    .cachedIn(viewModelScope)

}