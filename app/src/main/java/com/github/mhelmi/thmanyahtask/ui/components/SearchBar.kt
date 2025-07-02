package com.github.mhelmi.thmanyahtask.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.mhelmi.thmanyahtask.R


@Composable
fun AppSearchBar(
  query: String,
  onQueryChange: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  TextField(
    value = query,
    onValueChange = onQueryChange,
    placeholder = { Text(stringResource(R.string.search_hint)) },
    modifier = modifier
      .fillMaxWidth()
      .padding(16.dp),
    singleLine = true
  )
}

@Composable
@Preview
fun SearchBarPreview() {
  AppSearchBar(query = "", onQueryChange = {})
}