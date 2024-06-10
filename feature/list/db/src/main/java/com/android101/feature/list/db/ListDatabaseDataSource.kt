package com.android101.feature.list.db

import com.android101.feature.list.db.entity.TrackEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ListDatabaseDataSource @Inject constructor() {
    fun getTrackList(): Flow<List<TrackEntity>> {
        return flowOf(
            listOf(
                TrackEntity("1", "This is nice"),
                TrackEntity("2", "Another song"),
                TrackEntity("3", "Musketeers are alive"),
                TrackEntity("4", "Please be here"),
                TrackEntity("5", "Kiss me baby one more time"),
            ),
        )
    }

    suspend fun writeTrackList(tracks: List<TrackEntity>) {
        println(tracks)
    }
}
