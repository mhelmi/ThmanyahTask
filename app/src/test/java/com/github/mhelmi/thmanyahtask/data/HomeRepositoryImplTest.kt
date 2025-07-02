package com.github.mhelmi.thmanyahtask.data

import androidx.paging.testing.asSnapshot
import app.cash.turbine.test
import com.appmattus.kotlinfixture.kotlinFixture
import com.github.mhelmi.thmanyahtask.data.model.HomeSectionsResponse
import com.github.mhelmi.thmanyahtask.data.model.Pagination
import com.github.mhelmi.thmanyahtask.data.model.SearchResponse
import com.github.mhelmi.thmanyahtask.data.model.Section
import com.github.mhelmi.thmanyahtask.data.source.remote.HomeSectionsPagingSource
import com.github.mhelmi.thmanyahtask.data.source.remote.api.ApiService
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeRepositoryImplTest {

  private val apiService: ApiService = mockk()

  private val pagingSourceFactory: HomeSectionsPagingSource.Factory =
    object : HomeSectionsPagingSource.Factory {
      override fun create(limit: Int): HomeSectionsPagingSource {
        return HomeSectionsPagingSource(apiService, limit)
      }
    }
  private lateinit var homeRepository: HomeRepositoryImpl
  val fixture = kotlinFixture()

  @Before
  fun setup() {
    homeRepository = HomeRepositoryImpl(pagingSourceFactory, apiService)
  }

  @Test
  fun `getHomeSections returns paging data from HomeSectionsPagingSource`() = runTest {
    // Arrange
    val limit = 10
    val page = 1
    val fakeSections: List<Section> = fixture()
    val fakeResponse = HomeSectionsResponse(
      sections = fakeSections,
      pagination = Pagination(nextPage = "home/page=2", totalPages = 1)
    )

    coEvery { apiService.getHomeSections(page, limit) } returns fakeResponse

    // Act
    val resultFlow = homeRepository.getHomeSections(limit)

    // Assert
    val result = resultFlow.asSnapshot() // Collect the flow into a snapshot
    assertEquals(
      fakeSections.sortedBy { it.order }, // Verify the emitted paging data is sorted
      result
    )
  }

  @Test
  fun `search delegates query to apiService and returns results`() = runTest {
    // Arrange
    val query = "test query"
    val fakeSections: List<Section> = fixture()
    coEvery { apiService.search(query) } returns SearchResponse(results = fakeSections)

    // Act
    val resultFlow = homeRepository.search(query)

    // Assert
    resultFlow.test {
      assertEquals(fakeSections, awaitItem())
      awaitComplete()
    }
  }
}