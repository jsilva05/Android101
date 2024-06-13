package com.android101.backend.spotify.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetPlaylistResponse(
    @Json(name = "tracks") val tracks: PlaylistDto,
)

@JsonClass(generateAdapter = true)
data class PlaylistDto(
    @Json(name = "href") val href: String,
    @Json(name = "limit") val limit: Int,
    @Json(name = "next") val next: String?,
    @Json(name = "items") val items: List<PlaylistTrackDto>,
)
