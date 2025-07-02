package com.github.mhelmi.thmanyahtask.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
  navController: NavHostController,
  viewModel: SearchViewModel = hiltViewModel<SearchViewModel>(),
) {
  var query by remember { mutableStateOf("") } // User query
  val searchResults by viewModel.searchResults.collectAsState() // Observe search results
  val coroutineScope = rememberCoroutineScope()
  var searchJob: Job? = null

  Column(modifier = Modifier.fillMaxSize()) {
    // Search Bar
    TextField(
      value = query,
      onValueChange = {
        query = it
        // Debounce search
        searchJob?.cancel()
        searchJob = coroutineScope.launch {
          delay(200) // Wait 200ms
          if (query.isNotEmpty()) {
            viewModel.search(query)
          } else {
            viewModel.search("") // Clear results for empty query
          }
        }
      },
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      placeholder = { Text("Search...") }
    )

    // Search Results
//    LazyColumn(modifier = Modifier.fillMaxSize()) {
//      items(searchResults) { content ->
//        ContentCard(content)
//      }
//    }
  }
}