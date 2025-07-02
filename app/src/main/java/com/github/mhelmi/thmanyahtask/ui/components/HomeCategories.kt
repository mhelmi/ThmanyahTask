package com.github.mhelmi.thmanyahtask.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeCategories(
  categories: List<String>,
  selectedCategory: String,
  onCategorySelected: (String) -> Unit
) {
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
fun CategoriesPreview() {
  val sampleCategories = listOf("البودكاست", "المقالات الصوتية", "الكتب", "لك")
  var selectedCategory by remember { mutableStateOf(sampleCategories.first()) }

  HomeCategories(
    categories = sampleCategories,
    selectedCategory = selectedCategory,
    onCategorySelected = { category ->
      selectedCategory = category
    },
  )
}