package com.github.mhelmi.thmanyahtask.data.source.remote.api

import com.github.mhelmi.thmanyahtask.data.model.HomeSectionsResponse
import com.github.mhelmi.thmanyahtask.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

  @GET("home_sections")
  suspend fun getHomeSections(
    @Query("page") page: Int,
    @Query("limit") limit: Int
  ): HomeSectionsResponse

  @GET("search")
  suspend fun search(
    @Query("query") query: String,
    @Url url: String = "https://mock.apidog.com/m1/735111-711675-default/",
    ): SearchResponse
}