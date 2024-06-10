package com.android101.list.ui

import com.android101.list.model.Track

internal fun Track.toUiModel(): TrackUiModel =
    TrackUiModel(
        id = id.value,
        name = name,
    )
