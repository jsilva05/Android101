package com.android101.feature.list.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.db.SqlDriver
import com.android101.TrackEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TracksDao @Inject constructor(
    sqlDriver: SqlDriver,
) {
    private val database = Android101Database(sqlDriver)
    private val trackQueries = database.trackQueries

    fun getAll(): Flow<List<TrackEntity>> =
        trackQueries.selectAll()
            .asFlow()
            .mapToList(Dispatchers.IO)

    suspend fun insert(tracks: List<TrackEntity>) {
        trackQueries.transactionWithResult {
            tracks.forEach { track ->
                trackQueries.insert(track.id, track.name)
            }
        }
    }
}
