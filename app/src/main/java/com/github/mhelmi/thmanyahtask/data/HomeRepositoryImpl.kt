package com.github.mhelmi.thmanyahtask.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.mhelmi.thmanyahtask.data.model.Content
import com.github.mhelmi.thmanyahtask.data.model.Section
import com.github.mhelmi.thmanyahtask.data.source.remote.HomeSectionsPagingSource
import com.github.mhelmi.thmanyahtask.data.source.remote.api.ApiService
import com.github.mhelmi.thmanyahtask.domain.HomeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
  private val pagingSourceFactory: HomeSectionsPagingSource.Factory,
  private val apiService: ApiService // can be replaced with remote source
) : HomeRepository {
  // Home sections pagination
  override fun getHomeSections(limit: Int): Flow<PagingData<Section>> {
    return Pager(
      config = PagingConfig(pageSize = limit),
      pagingSourceFactory = { pagingSourceFactory.create(limit) }
    ).flow
  }

  // Search functionality
  override fun search(query: String): Flow<List<Content>> {
    return flow {
      delay(200) // Debounce for 200ms
      val response = apiService.search(query = query)
      emit(response.results)
    }.catch {
      emit(emptyList()) // Emit an empty list on error
    }
  }
}