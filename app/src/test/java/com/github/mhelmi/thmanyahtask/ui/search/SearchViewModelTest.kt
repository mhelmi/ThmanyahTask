package com.github.mhelmi.thmanyahtask.ui.search

import app.cash.turbine.test
import com.appmattus.kotlinfixture.kotlinFixture
import com.github.mhelmi.thmanyahtask.common.MainDispatcherRule
import com.github.mhelmi.thmanyahtask.data.model.Section
import com.github.mhelmi.thmanyahtask.domain.usecase.SearchUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  val fixture = kotlinFixture()

  private val searchUseCase: SearchUseCase = mockk()
  private lateinit var viewModel: SearchViewModel

  private val testDispatcher = StandardTestDispatcher()
  private val testScope = TestScope(testDispatcher)

  @Before
  fun setup() {
    viewModel = SearchViewModel(searchUseCase, testDispatcher)
  }

  @Test
  fun `updateSearchQuery updates the query state`() = testScope.runTest {
    // Arrange
    coEvery { searchUseCase("new query") } returns flow { emit(emptyList()) }

    // Act
    viewModel.updateSearchQuery("new query")

    // Assert
    assertEquals("new query", viewModel.query.value)
  }

  @Test
  fun `searchState is updated to Success when searchUseCase returns results`() = testScope.runTest {
    // Arrange
    val fakeResults: List<Section> = fixture()
    coEvery { searchUseCase("query") } returns flow { emit(fakeResults) }

    // Act
    viewModel.updateSearchQuery("query")
    advanceTimeBy(200) // Simulate debounce delay

    // Assert
    viewModel.searchState.test {
      assertEquals(SearchState.Empty, awaitItem())
      assertEquals(SearchState.Loading, awaitItem())
      assertEquals(SearchState.Success(fakeResults), awaitItem())
    }
  }

  @Test
  fun `searchState is updated to Empty when searchUseCase returns an empty list`() =
    testScope.runTest {
      // Arrange
      coEvery { searchUseCase("query") } returns flow { emit(emptyList()) }

      // Act
      viewModel.updateSearchQuery("query")
      advanceTimeBy(200) // Simulate debounce delay

      // Assert
      viewModel.searchState.test {
        assertEquals(SearchState.Empty, awaitItem())
        assertEquals(SearchState.Loading, awaitItem())
        assertEquals(SearchState.Empty, awaitItem())
      }
    }

  @Test
  fun `searchState is updated to Error when searchUseCase throws an exception`() =
    testScope.runTest {
      // Arrange
      val errorMessage = "Something went wrong"
      coEvery { searchUseCase("query") } returns flow { throw RuntimeException(errorMessage) }

      // Act
      viewModel.updateSearchQuery("query")
      advanceTimeBy(200) // Simulate debounce delay

      // Assert
      viewModel.searchState.test {
        assertEquals(SearchState.Empty, awaitItem())
        assertEquals(SearchState.Loading, awaitItem())
        assertEquals(SearchState.Error(errorMessage), awaitItem())
      }
    }

  @Test
  fun `search is not triggered for blank queries`() = testScope.runTest {
    // Arrange
    coEvery { searchUseCase(any()) } returns flow { emit(emptyList()) }

    // Act
    viewModel.updateSearchQuery("")
    advanceTimeBy(200) // Simulate debounce delay

    // Assert
    viewModel.searchState.test {
      // No search should be triggered, only initial state
      assertEquals(SearchState.Empty, awaitItem())
    }
  }

  @Test
  fun `retrySearch restarts the search with the last query`() = testScope.runTest {
    // Arrange
    val fakeResults: List<Section> = fixture()
    coEvery { searchUseCase("query") } returns flow { emit(fakeResults) }

    // Act
    viewModel.updateSearchQuery("query")
    advanceTimeBy(200) // Simulate debounce delay

    // Assert initial search
    viewModel.searchState.test {
      assertEquals(SearchState.Empty, awaitItem())
      assertEquals(SearchState.Loading, awaitItem())
      assertEquals(SearchState.Success(fakeResults), awaitItem())
    }

    // Act: Retry the search
    viewModel.retrySearch()

    // Assert retry
    viewModel.searchState.test {
      assertEquals(SearchState.Success(fakeResults), awaitItem())
    }
  }
}