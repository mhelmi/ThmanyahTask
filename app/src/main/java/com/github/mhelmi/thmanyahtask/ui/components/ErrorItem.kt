package com.github.mhelmi.thmanyahtask.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.mhelmi.thmanyahtask.R


@Composable
fun ErrorItem(
  modifier: Modifier = Modifier,
  onTryAgain: () -> Unit
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = stringResource(R.string.error_loading_more_items),
      color = Color.Red,
      modifier = Modifier
    )
    TextButton(
      onClick = onTryAgain,
      modifier = Modifier
        .padding(top = 16.dp)
    ) {
      Text(
        text = stringResource(R.string.try_again),
        color = Color.Gray,
      )
    }
  }
}

@Composable
@Preview
fun ErrorItemPreview() {
  ErrorItem {}
}