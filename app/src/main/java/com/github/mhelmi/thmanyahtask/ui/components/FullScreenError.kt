package com.github.mhelmi.thmanyahtask.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FullScreenError(
  contentPadding: PaddingValues,
  onRetry: () -> Unit
) {
  Box(
    modifier = Modifier
      .padding(contentPadding)
      .fillMaxSize()
  ) {
    ErrorItem(
      modifier = Modifier
        .align(Alignment.Center)
    ) {
      onRetry()
    }
  }
}

@Composable
@Preview
fun FullScreenErrorPreview() {
  FullScreenError(PaddingValues()) {

  }
}