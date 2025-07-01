package com.github.mhelmi.thmanyahtask.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.github.mhelmi.thmanyahtask.data.model.Section
import com.github.mhelmi.thmanyahtask.ui.components.SectionsList
import com.github.mhelmi.thmanyahtask.ui.components.TopBarWithFilters

@Composable
fun HomeScreen(
  navController: NavHostController,
  viewModel: HomeViewModel = hiltViewModel<HomeViewModel>()
) {
  val categories by viewModel.categories.collectAsStateWithLifecycle()
  val selectedCategory by viewModel.selectedCategory.collectAsStateWithLifecycle()
  val homeSections: LazyPagingItems<Section> = viewModel.homeSections.collectAsLazyPagingItems()
  Scaffold(
    topBar = {
      // Top Bar with Filter Categories
      TopBarWithFilters(
        username = "عبدالرحمن",
        notificationCount = 4,
        categories = categories.toList(),
        selectedCategory = selectedCategory,
        onCategorySelected = { category ->
          viewModel.selectCategory(category)
        },
        onSearchClick = {
          navController.navigate("search")
        }
      )
    }
  ) { paddingValues ->
    SectionsList(
      homeSections = homeSections,
      modifier = Modifier.padding(paddingValues),
    )
  }
}