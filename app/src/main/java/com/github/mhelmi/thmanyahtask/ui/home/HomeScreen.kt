package com.github.mhelmi.thmanyahtask.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.github.mhelmi.thmanyahtask.data.model.Section
import com.github.mhelmi.thmanyahtask.ui.components.FullScreenError
import com.github.mhelmi.thmanyahtask.ui.components.HomeTopBar
import com.github.mhelmi.thmanyahtask.ui.components.LoadingIndicator
import com.github.mhelmi.thmanyahtask.ui.components.NoItemFound
import com.github.mhelmi.thmanyahtask.ui.components.SectionsPaginationList

@Composable
fun HomeScreen(
  navController: NavHostController,
  viewModel: HomeViewModel = hiltViewModel<HomeViewModel>()
) {
  val homeSections: LazyPagingItems<Section> = viewModel.homeSections.collectAsLazyPagingItems()

  Scaffold(
    topBar = {
      HomeTopBar(
        username = "عبدالرحمن",
        notificationCount = 4,
        onSearchClick = {
          navController.navigate("search")
        }
      )
    }
  ) { paddingValues ->
    // Handle case when there's no data
    if (homeSections.itemCount == 0 && homeSections.loadState.refresh is LoadState.NotLoading) {
      NoItemFound(paddingValues)
    } else if (homeSections.loadState.refresh is LoadState.Loading) { // Show loading indicator for initial load
      LoadingIndicator(paddingValues)
    } else if (homeSections.loadState.hasError) { // Handle error during initial load
      FullScreenError(paddingValues) {
        homeSections.retry()
      }
    } else { // Show the list of sections
      SectionsPaginationList(
        homeSections = homeSections,
        modifier = Modifier.padding(paddingValues),
      )
    }
  }
}