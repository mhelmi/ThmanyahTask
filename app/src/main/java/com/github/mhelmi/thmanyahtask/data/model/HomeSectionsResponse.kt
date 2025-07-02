package com.github.mhelmi.thmanyahtask.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class HomeSectionsResponse(
  @SerializedName("sections") val sections: List<Section>,
  @SerializedName("pagination") val pagination: Pagination
)

@Keep
data class Section(
  @SerializedName("name") val name: String,
  @SerializedName("type") val type: String,
  @SerializedName("content_type") val contentType: String,
  @SerializedName("order") val order: String,
  @SerializedName("content") val content: List<Content>
)

@Keep
data class Content(
  @SerializedName("podcast_id") val podcastId: String,
  @SerializedName("name") val name: String,
  @SerializedName("description") val description: String,
  @SerializedName("avatar_url") val avatarUrl: String,
  @SerializedName("episode_count") val episodeCount: Int,
  @SerializedName("duration") val duration: Int
)

@Keep
data class SearchResponse(
  @SerializedName("sections") val results: List<Section>
)

@Keep
data class Pagination(
  @SerializedName("next_page") val nextPage: String?,
  @SerializedName("total_pages") val totalPages: Int
)