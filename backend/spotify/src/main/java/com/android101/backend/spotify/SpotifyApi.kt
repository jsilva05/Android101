package com.android101.backend.spotify

import com.android101.backend.spotify.dto.GetPlaylistResponse
import com.slack.eithernet.ApiResult
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import javax.inject.Singleton

interface SpotifyApi {
    @GET("playlists/37i9dQZEVXbMDoHDwVN2tF")
    suspend fun getTopMusics(): ApiResult<GetPlaylistResponse, Throwable>
}

@Module
@InstallIn(SingletonComponent::class)
internal object SpotifyApiModule {
    @Provides
    @Singleton
    fun provide(retrofit: Retrofit): SpotifyApi = retrofit.create()
}
