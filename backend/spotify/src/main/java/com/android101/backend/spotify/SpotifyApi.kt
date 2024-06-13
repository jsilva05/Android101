package com.android101.backend.spotify

import com.android101.backend.spotify.dto.GetPlaylistResponse
import com.android101.backend.spotify.dto.TokenResponseDto
import com.slack.eithernet.ApiResult
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Url
import javax.inject.Singleton

interface SpotifyApi {
    @POST
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    suspend fun getToken(
        @Url url: String,
        @Field("grant_type") grantType: String = "client_credentials",
        @Field("client_id") clientId: String = BuildConfig.SPOTIFY_API_CLIENT_ID,
        @Field("client_secret") clientSecret: String = BuildConfig.SPOTIFY_API_CLIENT_SECRET,
    ): ApiResult<TokenResponseDto, Throwable>

    @GET("playlists/37i9dQZEVXbMDoHDwVN2tF")
    suspend fun getTopMusics(
        @Header("Authorization") token: String,
    ): ApiResult<GetPlaylistResponse, Throwable>
}

@Module
@InstallIn(SingletonComponent::class)
internal object SpotifyApiModule {
    @Provides
    @Singleton
    fun provide(retrofit: Retrofit): SpotifyApi = retrofit.create()
}
