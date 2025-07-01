package com.github.mhelmi.thmanyahtask.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.github.mhelmi.thmanyahtask.R

@Composable
fun SectionHeader(
  title: String,
  titleColor: Color = Color.White,
  hasArrow: Boolean = true
) {
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
        color = titleColor
      ),
      maxLines = 1,
    )
    if (hasArrow) {
      Image(
        painter = painterResource(R.drawable.ic_right_arrow),
        contentDescription = "Arrow",
        colorFilter = ColorFilter.tint(Color.White),
      )
    }
  }
}


@Composable
@Preview
fun SectionHeaderPreview() {
  SectionHeader(title = "بودكاستات")
}

@Composable
@Preview
fun SectionHeaderNoArrowPreview() {
  SectionHeader(title = "الحلقات الجديدة", hasArrow = false)
}

@Composable
@Preview
fun SectionHeaderYellowColorPreview() {
  SectionHeader(title = "اسمع قبل الناس", titleColor = Color.Yellow)
}