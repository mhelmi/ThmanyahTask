package com.github.mhelmi.thmanyahtask.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.github.mhelmi.thmanyahtask.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithFilters(
  username: String,
  notificationCount: Int,
  categories: List<String>, // Dynamically loaded from content_type in API
  selectedCategory: String,
  onCategorySelected: (String) -> Unit,
  onSearchClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(modifier = modifier.background(Color.Black)) {
    TopAppBar(
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
    Spacer(modifier = Modifier.height(8.dp))
    // Filterable Categories
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
    ) {
      items(categories.size) { index ->
        val category = categories[index]
        FilterChip(
          text = category,
          isSelected = category == selectedCategory,
          onClick = { onCategorySelected(category) }
        )
      }
    }
  }
}

@Composable
fun FilterChip(
  text: String,
  isSelected: Boolean,
  onClick: () -> Unit
) {
  Surface(
    shape = CircleShape,
    color = if (isSelected) Color.Red else Color.DarkGray,
    modifier = Modifier
      .wrapContentSize()
      .clip(CircleShape)
      .clickable(onClick = onClick)
  ) {
    Text(
      text = text,
      style = MaterialTheme.typography.bodyMedium.copy(
        color = Color.White,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
      ),
      modifier = Modifier
        .padding(horizontal = 16.dp, vertical = 8.dp),
      textAlign = TextAlign.Center
    )
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBarWithFilters() {
  val sampleCategories = listOf("البودكاست", "المقالات الصوتية", "الكتب", "لك")
  var selectedCategory by remember { mutableStateOf(sampleCategories.first()) }

  TopBarWithFilters(
    username = "عبدالرحمن",
    notificationCount = 4,
    categories = sampleCategories,
    selectedCategory = selectedCategory,
    onCategorySelected = { category ->
      selectedCategory = category
    },
    onSearchClick = {

    }
  )
}