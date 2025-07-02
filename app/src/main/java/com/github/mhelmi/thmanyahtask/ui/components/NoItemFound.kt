package com.github.mhelmi.thmanyahtask.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.mhelmi.thmanyahtask.R

@Composable
fun NoItemFound(contentPadding: PaddingValues) {
  Box(
    modifier = Modifier
      .padding(contentPadding)
      .fillMaxSize()
  ) {
    Text(
      text = stringResource(R.string.error_no_item_found),
      modifier = Modifier
        .fillMaxWidth()
        .align(Alignment.Center)
        .padding(16.dp),
      color = Color.White,
      textAlign = TextAlign.Center
    )
  }
}

@Composable
@Preview
fun NoItemFoundPreview() {
  NoItemFound(contentPadding = PaddingValues())
}