package com.github.mhelmi.thmanyahtask.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.mhelmi.thmanyahtask.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
  username: String,
  notificationCount: Int,
  onSearchClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  TopAppBar(
    modifier = modifier,
    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black),
    title = {
      // Greeting and Profile
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
          text = stringResource(R.string.home_top_bar_title, username),
          style = MaterialTheme.typography.bodyMedium.copy(
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
          )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
          imageVector = Icons.Default.Settings, // Replace with your icon
          contentDescription = "Settings",
          tint = Color.Yellow
        )
      }
    },
    actions = {
      // Search icon
      IconButton(onClick = onSearchClick) {
        Icon(
          imageVector = Icons.Default.Search, // Replace with your icon
          contentDescription = "Search",
          tint = Color.White
        )
      }
      // Notifications
      Box {
        IconButton(onClick = { /* Handle notifications */ }) {
          Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Notifications",
            tint = Color.White
          )
        }
        if (notificationCount > 0) {
          Box(
            modifier = Modifier
              .size(16.dp)
              .clip(CircleShape)
              .background(Color.Red)
              .align(Alignment.TopEnd),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = "$notificationCount",
              style = MaterialTheme.typography.bodySmall.copy(
                color = Color.White,
                fontSize = 10.sp,
                textAlign = TextAlign.Center
              )
            )
          }
        }
      }
    }
  )
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBar() {
  HomeTopBar(
    username = "عبدالرحمن",
    notificationCount = 4,
    onSearchClick = {

    }
  )
}