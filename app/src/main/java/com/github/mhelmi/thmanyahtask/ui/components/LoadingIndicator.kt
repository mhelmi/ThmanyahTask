package com.github.mhelmi.thmanyahtask.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoadingIndicator(contentPadding: PaddingValues) {
  Box(
    modifier = Modifier
      .padding(contentPadding)
      .fillMaxSize()
  ) {
    CircularProgressIndicator(
      modifier = Modifier
        .align(Alignment.Center)
    )
  }
}

@Composable
@Preview
fun LoadingIndicatorPreview() {
  LoadingIndicator(PaddingValues())
}