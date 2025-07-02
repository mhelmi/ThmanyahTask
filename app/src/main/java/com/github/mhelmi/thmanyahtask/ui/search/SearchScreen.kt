package com.github.mhelmi.thmanyahtask.ui.search

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.github.mhelmi.thmanyahtask.ui.components.AppSearchBar
import com.github.mhelmi.thmanyahtask.ui.components.DrawSection
import com.github.mhelmi.thmanyahtask.ui.components.FullScreenError
import com.github.mhelmi.thmanyahtask.ui.components.LoadingIndicator
import com.github.mhelmi.thmanyahtask.ui.components.NoItemFound

@Composable
fun SearchScreen(
  navController: NavHostController,
  viewModel: SearchViewModel = hiltViewModel<SearchViewModel>(),
) {
  val query by viewModel.query.collectAsStateWithLifecycle()
  val searchState by viewModel.searchState.collectAsStateWithLifecycle()

  Scaffold(
    topBar = {
      Row(
        modifier = Modifier.padding(
          top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
        ),
        verticalAlignment = Alignment.CenterVertically
      ) {
        IconButton(onClick = {
          navController.navigateUp()
        }) {
          Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.White
          )
        }
        Spacer(modifier = Modifier.width(16.dp))
        AppSearchBar(
          modifier = Modifier.weight(1f),
          query = query,
          onQueryChange = { viewModel.updateSearchQuery(it) }
        )
      }
    }
  ) { paddingValues ->
    when (searchState) {
      is SearchState.Loading -> LoadingIndicator(paddingValues)
      is SearchState.Error -> FullScreenError(paddingValues) {
        viewModel.retrySearch()
      }

      is SearchState.Empty -> NoItemFound(paddingValues)
      is SearchState.Success -> {
        val results = (searchState as SearchState.Success).results
        LazyColumn(
          modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
        ) {
          items(results) { section ->
            DrawSection(section = section)
          }
        }
      }
    }
  }
}

