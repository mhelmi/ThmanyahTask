package com.github.mhelmi.thmanyahtask.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.mhelmi.thmanyahtask.data.model.Content
import com.github.mhelmi.thmanyahtask.data.model.Section
import com.github.mhelmi.thmanyahtask.ui.model.SectionType
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DrawSectionTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun rendersSquareSectionWhenSectionTypeIsSquare() {
    // Arrange
    val section = Section(
      name = "Square Section Title",
      type = SectionType.SQUARE.type,
      contentType = "podcast",
      order = "1",
      content = listOf(
        Content(
          podcastId = "1",
          name = "Item 1",
          description = "Description 1",
          avatarUrl = "image_url_1",
          episodeCount = 0,
          duration = 300
        ),
        Content(
          podcastId = "2",
          name = "Item 2",
          description = "Description 2",
          avatarUrl = "image_url_2",
          episodeCount = 0,
          duration = 600
        )
      )
    )

    // Act
    composeTestRule.setContent {
      DrawSection(section = section)
    }

    // Assert
    composeTestRule.onNodeWithText("Square Section Title").assertIsDisplayed()
    composeTestRule.onNodeWithText("Item 1").assertIsDisplayed()
    composeTestRule.onNodeWithText("Item 2").assertIsDisplayed()
  }

  @Test
  fun rendersTwoLinesGridSectionWhenSectionTypeIsTwoLinesGrid() {
    // Arrange
    val section = Section(
      name = "Grid Section Title",
      type = SectionType.TWO_LINES_GRID.type,
      contentType = "podcast",
      order = "2",
      content = listOf(
        Content(
          podcastId = "3",
          name = "Grid Item 1",
          description = "Description 3",
          avatarUrl = "image_url_3",
          episodeCount = 0,
          duration = 400
        )
      )
    )

    // Act
    composeTestRule.setContent {
      DrawSection(section = section)
    }

    // Assert
    composeTestRule.onNodeWithText("Grid Section Title").assertIsDisplayed()
    composeTestRule.onNodeWithText("Grid Item 1").assertIsDisplayed()
  }

  @Test
  fun rendersBigSquareSectionWhenSectionTypeIsBigSquare() {
    // Arrange
    val section = Section(
      name = "Big Square Section Title",
      type = SectionType.BIG_SQUARE.type,
      contentType = "podcast",
      order = "3",
      content = listOf(
        Content(
          podcastId = "4",
          name = "Big Item 1",
          description = "Description 4",
          avatarUrl = "image_url_4",
          episodeCount = 5,
          duration = 0
        )
      )
    )

    // Act
    composeTestRule.setContent {
      DrawSection(section = section)
    }

    // Assert
    composeTestRule.onNodeWithText("Big Square Section Title").assertIsDisplayed()
    composeTestRule.onNodeWithText("Big Item 1").assertIsDisplayed()
  }

  @Test
  fun rendersQueueSectionWhenSectionTypeIsQueue() {
    // Arrange
    val section = Section(
      name = "Queue Section Title",
      type = SectionType.QUEUE.type,
      contentType = "podcast",
      order = "4",
      content = listOf(
        Content(
          podcastId = "5",
          name = "Queue Item 1",
          description = "Published 1 hour ago",
          avatarUrl = "image_url_5",
          episodeCount = 0,
          duration = 7200
        ),
        Content(
          podcastId = "6",
          name = "Queue Item 2",
          description = "Published 2 hours ago",
          avatarUrl = "image_url_6",
          episodeCount = 0,
          duration = 3600
        )
      )
    )

    // Act
    composeTestRule.setContent {
      DrawSection(section = section)
    }

    // Assert
    composeTestRule.onNodeWithText("Queue Section Title").assertIsDisplayed()
    composeTestRule.onNodeWithText("Queue Item 1").assertIsDisplayed()
    composeTestRule.onNodeWithText("Published 1 hour ago").assertIsDisplayed()
  }

  @Test
  fun doesNotRenderAnythingWhenSectionTypeIsInvalid() {
    // Arrange
    val section = Section(
      name = "Invalid Section",
      type = "INVALID_TYPE",
      contentType = "podcast",
      order = "5",
      content = emptyList()
    )

    // Act
    composeTestRule.setContent {
      DrawSection(section = section)
    }

    // Assert
    composeTestRule.onNodeWithText("Invalid Section").assertDoesNotExist()
  }
}