package com.github.mhelmi.thmanyahtask.ui.components.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.github.mhelmi.thmanyahtask.ui.model.QueueItemUi

@Composable
fun QueueSection(
  title: String,
  episodeCount: Int,
  totalDuration: String,
  item: QueueItemUi
) {
  val density = LocalDensity.current // Get the current Density

  Column(modifier = Modifier.padding(16.dp)) {
    // Section Title + Content Count and Duration
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall.copy(
          fontSize = 20.sp,
          fontWeight = FontWeight.Bold,
          color = Color.White
        ),
        maxLines = 1,
      )
      Text(
        text = "$episodeCount حلقات · $totalDuration",
        style = MaterialTheme.typography.bodySmall.copy(
          fontSize = 14.sp,
          color = Color.Gray
        ),
        maxLines = 1,
      )
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Queue Content
    Card(
      shape = RoundedCornerShape(16.dp),
      modifier = Modifier
        .fillMaxWidth()
        .height(140.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(Color.DarkGray)
    ) {
      Row(
        modifier = Modifier
          .fillMaxSize()
          .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Play Button
        Box(
          modifier = Modifier
            .size(48.dp)
            .background(Color.White, shape = CircleShape),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "Play Button",
            tint = Color.Black,
            modifier = Modifier.size(32.dp)
          )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Description
        Column(
          modifier = Modifier.weight(1f)
        ) {
          Text(
            text = item.title,
            style = MaterialTheme.typography.bodyMedium.copy(
              fontWeight = FontWeight.Bold,
              color = Color.White,
              fontSize = 16.sp
            ),
            maxLines = 2
          )

          Spacer(modifier = Modifier.height(8.dp))

          Row {
            Text(
              modifier = Modifier.weight(1f),
              text = item.publishedTime,
              style = MaterialTheme.typography.bodySmall.copy(
                color = Color.Red,
                fontSize = 14.sp
              ),
              maxLines = 1,
            )
            Text(
              modifier = Modifier.weight(1f),
              text = item.duration,
              style = MaterialTheme.typography.bodySmall.copy(
                color = Color(0xFFA6A7AA),
                fontSize = 14.sp
              ),
              maxLines = 1,
            )
          }

        }

        Spacer(modifier = Modifier.width(8.dp))

        // Overlapping Images
        Box(
          modifier = Modifier.size(120.dp),
          contentAlignment = Alignment.CenterEnd
        ) {
          item.queueImages.forEachIndexed { index, imageUrl ->
            Image(
              painter = rememberAsyncImagePainter(imageUrl),
              contentDescription = "Queue Image $index",
              contentScale = ContentScale.Crop,
              modifier = Modifier
                .size(100.dp)
                .graphicsLayer(
                  translationX = with(density) {
                    (-16 * index).dp.toPx() // Overlap effect
                  }
                )
                .clip(RoundedCornerShape(8.dp))
            )
          }
        }
      }
    }
  }
}

@Composable
@Preview
fun QueueSectionPreview() {
  val sampleItem = QueueItemUi(
    title = "السفر الصامت وضوضاء الإعلانات",
    duration = "23 ساعة",
    publishedTime = "قبل 5 ساعات",
    queueImages = listOf(
      "https://via.placeholder.com/150",
      "https://via.placeholder.com/150",
      "https://via.placeholder.com/150"
    )
  )

  QueueSection(
    title = "الطابور",
    episodeCount = 4,
    totalDuration = "7 ساعات",
    item = sampleItem
  )
}