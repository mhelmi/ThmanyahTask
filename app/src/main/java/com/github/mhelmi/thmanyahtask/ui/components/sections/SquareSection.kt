package com.github.mhelmi.thmanyahtask.ui.components.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.github.mhelmi.thmanyahtask.ui.components.DurationText
import com.github.mhelmi.thmanyahtask.ui.components.SectionHeader
import com.github.mhelmi.thmanyahtask.ui.model.SquareItemUi

@Composable
fun SquareSection(
  title: String,
  items: List<SquareItemUi> // List of content items for the section
) {
  Column(modifier = Modifier.padding(16.dp)) {
    // Section Title + Arrow
    SectionHeader(
      title = title,
      titleColor = Color.Yellow
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Horizontal List of Square Items
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(16.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      items(items.size) { index ->
        SquareContentCard(item = items[index])
      }
    }
  }
}

@Composable
fun SquareContentCard(item: SquareItemUi) {
  Column(
    modifier = Modifier
      .width(120.dp) // Square size
  ) {
    // Image
    Card(
      shape = RoundedCornerShape(8.dp),
      modifier = Modifier.size(120.dp)
    ) {
      AsyncImage(
        model = item.imageUrl,
        contentDescription = "Content Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
      )
    }

    Spacer(modifier = Modifier.height(8.dp))
    // Title
    Text(
      modifier = Modifier.fillMaxWidth(),
      text = item.title,
      style = MaterialTheme.typography.bodyMedium.copy(
        fontWeight = FontWeight.Bold,
        color = Color.White
      ),
      maxLines = 1,
      overflow = TextOverflow.Ellipsis,
    )

    Spacer(modifier = Modifier.height(4.dp))

    // Duration and Date
    Row(
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.fillMaxWidth()
    ) {
      DurationText(duration = item.duration)
      Spacer(modifier = Modifier.width(4.dp))
      Text(
        text = item.date,
        style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
        color = Color.Gray,
        maxLines = 1,
      )
    }
  }
}


@Composable
@Preview
fun SquareSectionPreview() {
  val sampleItems = listOf(
    SquareItemUi(
      imageUrl = "https://via.placeholder.com/150",
      title = "جادي",
      duration = 3432,
      date = "أمس"
    ),
    SquareItemUi(
      imageUrl = "https://via.placeholder.com/150",
      title = "فنجان",
      duration = 3132,
      date = "أمس"
    ),
    SquareItemUi(
      imageUrl = "https://via.placeholder.com/150",
      title = "بودكاست",
      duration = 31323,
      date = "أول أمس"
    )
  )

  SquareSection(
    title = "اسمع قبل الناس",
    items = sampleItems
  )
}