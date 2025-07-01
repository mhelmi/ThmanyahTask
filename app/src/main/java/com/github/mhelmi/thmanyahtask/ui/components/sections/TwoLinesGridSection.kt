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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
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
fun TwoLinesGridSection(
  title: String,
  items: List<SquareItemUi>
) {
  Column(modifier = Modifier.padding(16.dp)) {
    // Section Title + Arrow
    SectionHeader(title = title)

    Spacer(modifier = Modifier.height(8.dp))

    // 2-Column Grid
    LazyHorizontalGrid(
      rows = GridCells.Fixed(2),
      horizontalArrangement = Arrangement.spacedBy(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp),
      modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
    ) {
      items(items.size) { index ->
        GridContentCard(item = items[index])
      }
    }
  }
}

@Composable
fun GridContentCard(item: SquareItemUi) {
  Row(
    modifier = Modifier
      .width(300.dp)
      .padding(8.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    // Image
    Card(
      shape = RoundedCornerShape(8.dp),
      modifier = Modifier.size(80.dp)
    ) {
      AsyncImage(
        model = item.imageUrl,
        contentDescription = "Content Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
      )
    }

    Spacer(modifier = Modifier.width(8.dp))

    // Text Content
    Column(
      verticalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier.weight(1f)
    ) {
      // Title
      Text(
        text = item.title,
        style = MaterialTheme.typography.bodyMedium.copy(
          fontWeight = FontWeight.Bold,
          fontSize = 16.sp
        ),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
      )

      Spacer(modifier = Modifier.height(4.dp))

      // Duration and Date
      Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
      ) {
        Text(
          modifier = Modifier.weight(1f),
          text = item.date,
          style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
          color = Color.Gray,
          maxLines = 1,
        )
        Spacer(modifier = Modifier.width(4.dp))
        DurationText(
          modifier = Modifier.wrapContentSize(),
          duration = item.duration,
        )
      }
    }
  }
}

@Composable
@Preview
fun TwoLinesGridSectionPreview() {
  val sampleItems = listOf(
    SquareItemUi(
      imageUrl = "https://via.placeholder.com/150",
      title = "د. فيصل الرفاعي | هل يستطيع الإنسان مواكبة التطور؟",
      duration = 32434,
      date = "قبل 5 ساعات"
    ),
    SquareItemUi(
      imageUrl = "https://via.placeholder.com/150",
      title = "The Subscription Trap",
      duration = 32464,
      date = "قبل 5 ساعات"
    ),
    SquareItemUi(
      imageUrl = "https://via.placeholder.com/150",
      title = "جادي | بودكاست جديد",
      duration = 31434,
      date = "قبل 3 ساعات"
    ),
    SquareItemUi(
      imageUrl = "https://via.placeholder.com/150",
      title = "Planet Money: It's a Trap",
      duration = 3234,
      date = "قبل 10 ساعات"
    ),
    SquareItemUi(
      imageUrl = "https://via.placeholder.com/150",
      title = "د. فيصل الرفاعي | هل يستطيع الإنسان مواكبة التطور؟",
      duration = 42494,
      date = "قبل 5 ساعات"
    ),
    SquareItemUi(
      imageUrl = "https://via.placeholder.com/150",
      title = "The Subscription Trap",
      duration = 72494,
      date = "قبل 5 ساعات"
    ),
    SquareItemUi(
      imageUrl = "https://via.placeholder.com/150",
      title = "جادي | بودكاست جديد",
      duration = 12494,
      date = "قبل 3 ساعات"
    ),
    SquareItemUi(
      imageUrl = "https://via.placeholder.com/150",
      title = "Planet Money: It's a Trap",
      duration = 22494,
      date = "قبل 10 ساعات"
    )
  )

  TwoLinesGridSection(
    title = "الحلقات الجديدة",
    items = sampleItems
  )
}