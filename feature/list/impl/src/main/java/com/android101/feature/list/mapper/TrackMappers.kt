package com.android101.feature.list.mapper

import com.android101.TrackEntity
import com.android101.backend.spotify.dto.TrackDto
import com.android101.list.model.Track
import com.android101.list.model.TrackId

internal fun TrackDto.toEntity(): TrackEntity =
    TrackEntity(
        id = id,
        name = name,
    )

internal fun TrackEntity.toDomain(): Track =
    Track(
        id = TrackId(id),
        name = name,
    )
