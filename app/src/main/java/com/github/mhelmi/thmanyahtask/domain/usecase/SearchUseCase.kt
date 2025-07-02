package com.github.mhelmi.thmanyahtask.domain.usecase

import com.github.mhelmi.thmanyahtask.domain.HomeRepository
import com.github.mhelmi.thmanyahtask.ui.model.SectionType
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchUseCase @Inject constructor(
  private val homeRepository: HomeRepository,
) {
  operator fun invoke(query: String) = homeRepository.search(query)
    .map { list ->
      list.map { section ->
        if (SectionType.fromType(section.type) != null) {
          section
        } else {
          section.copy(type = SectionType.SQUARE.type)
        }
      }
    }
}