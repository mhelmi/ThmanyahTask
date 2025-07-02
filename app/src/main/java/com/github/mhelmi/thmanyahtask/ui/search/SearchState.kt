package com.github.mhelmi.thmanyahtask.ui.search

import com.github.mhelmi.thmanyahtask.data.model.Section

sealed class SearchState {
  data object Loading : SearchState()
  data class Success(
    val results: List<Section>
  ) : SearchState()

  data class Error(val message: String) : SearchState()
  data object Empty : SearchState()
}
