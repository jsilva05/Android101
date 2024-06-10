package com.android101.backend.spotify.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlaylistTrackDto(
    @Json(name = "track") val track: TrackDto,
)

sealed interface TrackDto {
    val id: String
    val type: String
    val name: String

    @JsonClass(generateAdapter = true)
    data class TrackObjectDto(
        @Json(name = "id") override val id: String,
        @Json(name = "type") override val type: String,
        @Json(name = "name") override val name: String,
    ) : TrackDto

    @JsonClass(generateAdapter = true)
    data class EpisodeObjectDto(
        @Json(name = "id") override val id: String,
        @Json(name = "type") override val type: String,
        @Json(name = "name") override val name: String,
    ) : TrackDto
}
