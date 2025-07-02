package com.github.mhelmi.thmanyahtask.utils.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.github.mhelmi.thmanyahtask.R

@Composable
fun Int.toReadableDuration(): String {
  val hours = this / 3600 // 1 hour = 3600 seconds
  val minutes = (this % 3600) / 60 // Remaining seconds converted to minutes

  return if (hours > 0 && minutes > 0) {
    stringResource(R.string.duration_format, hours, minutes)
  } else if (hours > 0) {
    stringResource(R.string.hours_format, hours)
  } else if (minutes > 0) {
    stringResource(R.string.minutes_format, minutes)
  } else {
    ""
  }
}