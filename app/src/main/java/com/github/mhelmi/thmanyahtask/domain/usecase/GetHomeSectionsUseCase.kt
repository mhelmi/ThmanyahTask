package com.github.mhelmi.thmanyahtask.domain.usecase

import com.github.mhelmi.thmanyahtask.domain.HomeRepository
import javax.inject.Inject

class GetHomeSectionsUseCase @Inject constructor(
  private val homeRepository: HomeRepository,
) {
  operator fun invoke(limit: Int) = homeRepository.getHomeSections(limit)
}