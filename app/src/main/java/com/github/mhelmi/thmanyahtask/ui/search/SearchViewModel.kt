package com.github.mhelmi.thmanyahtask.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mhelmi.thmanyahtask.di.IoDispatcher
import com.github.mhelmi.thmanyahtask.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
  private val searchUseCase: SearchUseCase,
  @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

  private val _query = MutableStateFlow("")
  val query = _query.asStateFlow()

  private val _searchState = MutableStateFlow<SearchState>(SearchState.Empty)
  val searchState: StateFlow<SearchState> = _searchState

  private var searchJob: Job? = null

  init {
    observeSearchQuery()
  }

  fun updateSearchQuery(newQuery: String) {
    _query.value = newQuery
  }

  private fun observeSearchQuery() {
    query
      .debounce(200) // Delay 200ms after user stops typing
      .filter { it.isNotBlank() }
      .distinctUntilChanged() // Avoid repeated requests for the same query
      .onEach { performSearch(it) }
      .flowOn(ioDispatcher)
      .launchIn(viewModelScope)
  }

  private fun performSearch(searchQuery: String) {
    searchJob?.cancel() // Cancel any ongoing search job
    searchJob = searchUseCase(searchQuery)
      .onStart {
        _searchState.value = SearchState.Loading
      }
      .onEach {
        _searchState.value = if (it.isEmpty()) {
          SearchState.Empty
        } else {
          SearchState.Success(it)
        }
      }
      .catch {
        it.printStackTrace()
        _searchState.value = SearchState.Error(it.message ?: "Unknown error")
      }
      .flowOn(ioDispatcher)
      .launchIn(viewModelScope)
  }

  fun retrySearch() {
    performSearch(query.value)
  }
}