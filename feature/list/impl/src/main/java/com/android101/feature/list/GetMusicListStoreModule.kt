package com.android101.feature.list

import com.android101.backend.spotify.SpotifyNetworkDataSource
import com.android101.core.impl.toFetcherResult
import com.android101.feature.list.db.ListDatabaseDataSource
import com.android101.feature.list.mapper.toDomain
import com.android101.feature.list.mapper.toEntity
import com.android101.list.model.Track
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.map
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.StoreBuilder
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object GetMusicListStoreModule {
    @Provides
    @Singleton
    fun provideMusicListStore(
        spotifyNetworkDataSource: SpotifyNetworkDataSource,
        listDatabaseDataSource: ListDatabaseDataSource,
    ): Store<Unit, List<Track>> {
        return StoreBuilder.from(
            fetcher = Fetcher.ofResult { _: Unit ->
                spotifyNetworkDataSource
                    .getTopMusics()
                    .toFetcherResult()
            },
            sourceOfTruth = SourceOfTruth.of(
                reader = {
                    listDatabaseDataSource.getTrackList().map { trackList ->
                        trackList.map { it.toDomain() }
                    }
                },
                writer = { _, response ->
                    val tracks = response.items.map { it.track.toEntity() }
                    listDatabaseDataSource.writeTrackList(tracks)
                },
            ),
        ).build()
    }
}
