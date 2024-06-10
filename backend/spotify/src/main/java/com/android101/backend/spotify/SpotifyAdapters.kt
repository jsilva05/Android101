package com.android101.backend.spotify

import com.android101.backend.spotify.dto.TrackDto
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
internal object SpotifyAdapters {
    @Provides
    @IntoSet
    fun provideTracksAdapter(): JsonAdapter.Factory {
        return PolymorphicJsonAdapterFactory.of(TrackDto::class.java, "type")
            .withSubtype(TrackDto.TrackObjectDto::class.java, "track")
            .withSubtype(TrackDto.EpisodeObjectDto::class.java, "episode")
    }
}
