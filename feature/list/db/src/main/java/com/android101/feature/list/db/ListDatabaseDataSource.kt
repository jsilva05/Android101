package com.android101.feature.list.db

import com.android101.TrackEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListDatabaseDataSource @Inject constructor(
    private val tracksDao: TracksDao,
) {
    fun getTrackList(): Flow<List<TrackEntity>> =
        tracksDao.getAll()

    suspend fun writeTrackList(tracks: List<TrackEntity>) {
        tracksDao.insert(tracks)
    }
}
