package com.github.mhelmi.thmanyahtask.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
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
fun DrawSection(section: Section) {
  when (SectionType.fromType(section.type)) {
    SectionType.SQUARE -> {
      SquareSection(
        title = section.name,
        items = section.content.map { content ->
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
        title = section.name,
        items = section.content.map { content ->
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
        title = section.name,
        items = section.content.map { content ->
          BigSquareItemUi(
            imageUrl = content.avatarUrl,
            title = content.name,
            episodeCount = content.episodeCount
          )
        }
      )
    }

    SectionType.QUEUE -> {
      section.content.firstOrNull()?.let { queueContent ->
        QueueSection(
          title = section.name,
          episodeCount = section.content.size,
          totalDuration = stringResource(
            R.string.hours_format,
            (section.content.sumOf { content -> content.duration } / 3600)),
          item = QueueItemUi(
            title = queueContent.name,
            duration = queueContent.duration.toReadableDuration(),
            publishedTime = queueContent.description,
            queueImages = section.content.take(4).map { content -> content.avatarUrl }
          )
        )
      }
    }

    null -> {}
  }
}