package com.github.mhelmi.thmanyahtask.ui.components.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.github.mhelmi.thmanyahtask.ui.components.SectionHeader
import com.github.mhelmi.thmanyahtask.ui.model.BigSquareItemUi
import com.github.mhelmi.thmanyahtask.R

@Composable
fun BigSquareSection(
  title: String,
  items: List<BigSquareItemUi>
) {
  Column(modifier = Modifier.padding(16.dp)) {
    // Section Title
    SectionHeader(title = title, hasArrow = false)

    Spacer(modifier = Modifier.height(8.dp))

    // Horizontal List of Big Square Items
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(16.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      items(items.size) { index ->
        BigSquareContentCard(item = items[index])
      }
    }
  }
}

@Composable
fun BigSquareContentCard(item: BigSquareItemUi) {
  Box(
    modifier = Modifier
      .width(250.dp)
      .height(200.dp)
      .padding(8.dp)
  ) {
    // Background Image
    Card(
      shape = RoundedCornerShape(8.dp),
      modifier = Modifier.fillMaxSize()
    ) {
      Image(
        painter = rememberAsyncImagePainter(item.imageUrl),
        contentDescription = "Content Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
      )
    }

    // Text Overlay
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(
          brush = Brush.verticalGradient(
            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
            startY = 100f
          )
        ),
      contentAlignment = Alignment.BottomStart
    ) {
      Column(modifier = Modifier.padding(8.dp)) {
        // Title
        Text(
          text = item.title,
          style = MaterialTheme.typography.bodyMedium.copy(
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
          ),
          maxLines = 1
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Episode Count
        if (item.episodeCount > 0) {
          Text(
            text = stringResource(R.string.episode_count_format, item.episodeCount),
            style = MaterialTheme.typography.bodySmall.copy(
              color = Color.White,
              fontSize = 12.sp
            ),
            maxLines = 1,
          )
        }
      }
    }
  }
}

@Composable
@Preview
fun BigSquareSectionPreview() {
  val sampleItems = listOf(
    BigSquareItemUi(
      imageUrl = "https://via.placeholder.com/300",
      title = "إذا ودك تضحك",
      episodeCount = 10
    ),
    BigSquareItemUi(
      imageUrl = "https://via.placeholder.com/300",
      title = "من ذاكرة تاريخ العرب",
      episodeCount = 20
    ),
    BigSquareItemUi(
      imageUrl = "https://via.placeholder.com/300",
      title = "بودكاست ثقافي",
      episodeCount = 15
    )
  )

  BigSquareSection(
    title = "توصيات الفريق",
    items = sampleItems
  )
}