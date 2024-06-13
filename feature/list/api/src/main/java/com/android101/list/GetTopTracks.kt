package com.android101.list

import app.cash.quiver.Outcome
import com.android101.list.model.Track
import kotlinx.coroutines.flow.Flow

interface GetTopTracks {
    operator fun invoke(): Flow<Outcome<Error, List<Track>>>

    sealed interface Error {
        data class Generic(
            val cause: Throwable,
        ) : Error

        data class Authentication(
            val cause: Throwable,
        ) : Error
    }
}
