package com.github.mhelmi.thmanyahtask.domain.usecase

import app.cash.turbine.test
import com.appmattus.kotlinfixture.kotlinFixture
import com.github.mhelmi.thmanyahtask.data.model.Section
import com.github.mhelmi.thmanyahtask.domain.HomeRepository
import com.github.mhelmi.thmanyahtask.ui.model.SectionType
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchUseCaseTest {

  private val homeRepository: HomeRepository = mockk()
  private lateinit var searchUseCase: SearchUseCase

  val fixture = kotlinFixture()

  @Before
  fun setup() {
    searchUseCase = SearchUseCase(homeRepository)
  }

  @Test
  fun `when call invoke delegates query to homeRepository and returns sections`() = runTest {
    // Arrange
    val query = "test query"
    val fakeSections: List<Section> = fixture<List<Section>>().mapIndexed { index, section ->
      if (index == 0) {
        section.copy(type = SectionType.QUEUE.type)
      } else {
        section.copy(type = "invalid_type")
      }
    }

    coEvery { homeRepository.search(query) } returns flowOf(fakeSections)

    // Act
    val resultFlow = searchUseCase(query)

    // Assert
    resultFlow.test {
      // Verify emitted list is transformed correctly
      val result = awaitItem()
      assertEquals(fakeSections.size, result.size)
      assertEquals(fakeSections[0], result[0]) // First item is valid, remains unchanged
      assertEquals(
        fakeSections[1].copy(type = SectionType.SQUARE.type), result[1]
      ) // Second item is invalid, type is updated

      awaitComplete()
    }

    // Verify repository method is called with the proper query
    verify { homeRepository.search(query) }
  }

  @Test
  fun `when call invoke returns empty list when repository emits empty list`() = runTest {
    // Arrange
    val query = "empty query"
    coEvery { homeRepository.search(query) } returns flowOf(emptyList())

    // Act
    val resultFlow = searchUseCase(query)

    // Assert
    resultFlow.test {
      assertEquals(emptyList<Section>(), awaitItem()) // Verify emitted list is empty
      awaitComplete()
    }

    // Verify repository method is called with the proper query
    verify { homeRepository.search(query) }
  }
}