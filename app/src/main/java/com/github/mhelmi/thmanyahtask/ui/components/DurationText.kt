package com.github.mhelmi.thmanyahtask.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.mhelmi.thmanyahtask.utils.extensions.toReadableDuration

@Composable
fun DurationText(
  duration: Int,
  modifier: Modifier = Modifier,
) {
  val durationText = duration.toReadableDuration()
  if (durationText.isNotEmpty()) {
    Box(
      modifier = modifier
        .background(
          color = Color(0xFF2C2A43),
          shape = RoundedCornerShape(16.dp)
        )
        .padding(horizontal = 8.dp, vertical = 4.dp) // Padding inside the box
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Text(
          text = durationText,
          color = Color.White,
          style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
          ),
          maxLines = 1,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
          imageVector = Icons.Default.PlayArrow,
          contentDescription = "Play",
          tint = Color.White,
          modifier = Modifier.size(16.dp)
        )
      }
    }
  }
}

@Preview
@Composable
fun DurationTextPreview() {
  Column {
    DurationText(23435)
    Spacer(modifier = Modifier.height(16.dp))
    DurationText(3600)
    Spacer(modifier = Modifier.height(16.dp))
    DurationText(60)
    Spacer(modifier = Modifier.height(16.dp))
    DurationText(0)
  }
}