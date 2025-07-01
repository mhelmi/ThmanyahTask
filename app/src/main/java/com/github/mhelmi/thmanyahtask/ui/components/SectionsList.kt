package com.github.mhelmi.thmanyahtask.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.github.mhelmi.thmanyahtask.R
import com.github.mhelmi.thmanyahtask.data.model.Section
import com.github.mhelmi.thmanyahtask.ui.components.sections.BigSquareSection
import com.github.mhelmi.thmanyahtask.ui.components.sections.QueueSection
import com.github.mhelmi.thmanyahtask.ui.components.sections.SquareSection
import com.github.mhelmi.thmanyahtask.ui.components.sections.TwoLinesGridSection
import com.github.mhelmi.thmanyahtask.ui.model.BigSquareItemUi
import com.github.mhelmi.thmanyahtask.ui.model.QueueItemUi
import com.github.mhelmi.thmanyahtask.ui.model.SectionType
import com.github.mhelmi.thmanyahtask.ui.model.SquareItemUi
import com.github.mhelmi.thmanyahtask.utils.extensions.toReadableDuration

@Composable
fun SectionsList(
  homeSections: LazyPagingItems<Section>,
  modifier: Modifier = Modifier,
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
  ) {
    items(
      count = homeSections.itemCount,
      key = homeSections.itemKey()
    ) { index ->
      val section = homeSections[index]
      section?.let {
        when (SectionType.fromType(it.type)) {
          SectionType.SQUARE -> {
            SquareSection(
              title = it.name,
              items = it.content.map { content ->
                SquareItemUi(
                  imageUrl = content.avatarUrl,
                  title = content.name,
                  duration = content.duration,
                  date = content.description
                )
              }
            )
          }

          SectionType.TWO_LINES_GRID -> {
            TwoLinesGridSection(
              title = it.name,
              items = it.content.map { content ->
                SquareItemUi(
                  imageUrl = content.avatarUrl,
                  title = content.name,
                  duration = content.duration,
                  date = content.description
                )
              }
            )
          }

          SectionType.BIG_SQUARE -> {
            BigSquareSection(
              title = it.name,
              items = it.content.map { content ->
                BigSquareItemUi(
                  imageUrl = content.avatarUrl,
                  title = content.name,
                  episodeCount = content.episodeCount
                )
              }
            )
          }

          SectionType.QUEUE -> {
            it.content.firstOrNull()?.let { queueContent ->
              QueueSection(
                title = it.name,
                episodeCount = it.content.size,
                totalDuration = stringResource(
                  R.string.hours_format,
                  (it.content.sumOf { content -> content.duration } / 60)),
                item = QueueItemUi(
                  title = queueContent.name,
                  duration = queueContent.duration.toReadableDuration(),
                  publishedTime = queueContent.description,
                  queueImages = it.content.take(4).map { content -> content.avatarUrl }
                )
              )
            }
          }

          null -> {}
        }

      }

    }
  }
}