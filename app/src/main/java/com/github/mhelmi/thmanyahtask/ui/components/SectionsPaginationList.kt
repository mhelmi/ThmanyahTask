package com.github.mhelmi.thmanyahtask.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.github.mhelmi.thmanyahtask.data.model.Section

@Composable
fun SectionsPaginationList(
  homeSections: LazyPagingItems<Section>,
  modifier: Modifier = Modifier,
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
  ) {
    items(
      count = homeSections.itemCount,
      key = homeSections.itemKey()
    ) { index ->
      val section = homeSections[index]
      section?.let {
        DrawSection(section)
      }
    }

    item {
      // Show loading indicator for pagination (append)
      val appendLoading = homeSections.loadState.append is LoadState.Loading
      if (appendLoading) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
        ) {
          CircularProgressIndicator(
            modifier = Modifier
              .align(Alignment.Center)
              .padding(16.dp)
          )
        }
      }

      // Handle error during pagination (append)
      val appendError = homeSections.loadState.append as? LoadState.Error
      appendError?.let {
        ErrorItem {
          homeSections.retry()
        }
      }
    }
  }
}