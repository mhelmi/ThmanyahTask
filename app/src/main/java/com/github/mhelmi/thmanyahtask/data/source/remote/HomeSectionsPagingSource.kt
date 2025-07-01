package com.github.mhelmi.thmanyahtask.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.mhelmi.thmanyahtask.data.model.Section
import com.github.mhelmi.thmanyahtask.data.source.remote.api.ApiService
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class HomeSectionsPagingSource @AssistedInject constructor(
  private val apiService: ApiService,
  @Assisted private val limit: Int
) : PagingSource<Int, Section>() {

//  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Section> {
//    val page = params.key ?: 1
//    return try {
//      val response = apiService.getHomeSections(page, params.loadSize)
//      LoadResult.Page(
//        data = response.sections,
//        prevKey = if (page == 1) null else page - 1,
//        nextKey = if (response.pagination.nextPage.isNullOrEmpty()) null else page + 1
//      )
//    } catch (e: Exception) {
//      LoadResult.Error(e)
//    }
//  }
//
//  override fun getRefreshKey(state: PagingState<Int, Section>): Int? {
//    return state.anchorPosition
//  }


  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Section> {
    val page = params.key ?: 1
    return try {
      val response = apiService.getHomeSections(page, limit)
      val totalPages = response.pagination.totalPages

      LoadResult.Page(
        data = response.sections,
        prevKey = if (page == 1) null else page - 1,
        nextKey = if (page >= totalPages) null else page + 1 // Stop pagination if all pages are loaded
      )
    } catch (e: Exception) {
      LoadResult.Error(e)
    }
  }

  override fun getRefreshKey(state: PagingState<Int, Section>): Int? {
    return state.anchorPosition?.let { anchor ->
      state.closestPageToPosition(anchor)?.prevKey?.plus(1)
        ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
    }
  }

  @AssistedFactory
  interface Factory {
    fun create(limit: Int): HomeSectionsPagingSource
  }
}