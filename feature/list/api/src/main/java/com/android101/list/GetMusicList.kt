package com.android101.list

import app.cash.quiver.Outcome
import com.android101.list.model.Track
import kotlinx.coroutines.flow.Flow

interface GetMusicList {
    operator fun invoke(): Flow<Outcome<Error, List<Track>>>

    data class Error(
        val cause: Throwable,
    )
}
