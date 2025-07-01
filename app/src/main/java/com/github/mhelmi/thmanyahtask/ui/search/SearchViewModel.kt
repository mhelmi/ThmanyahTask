package com.github.mhelmi.thmanyahtask.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mhelmi.thmanyahtask.data.model.Content
import com.github.mhelmi.thmanyahtask.domain.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
  private val repository: HomeRepository
) : ViewModel() {

  // Search state
  private val _searchResults = MutableStateFlow<List<Content>>(emptyList())
  val searchResults: StateFlow<List<Content>> = _searchResults.asStateFlow()

  // Perform search
  fun search(query: String) {
    viewModelScope.launch {
      repository.search(query).collect { results ->
        _searchResults.value = results
      }
    }
  }
}