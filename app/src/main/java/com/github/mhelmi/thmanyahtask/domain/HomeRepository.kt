package com.github.mhelmi.thmanyahtask.domain

import androidx.paging.PagingData
import com.github.mhelmi.thmanyahtask.data.model.Content
import com.github.mhelmi.thmanyahtask.data.model.Section
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
  fun getHomeSections(limit: Int): Flow<PagingData<Section>>
  fun search(query: String): Flow<List<Content>>
}