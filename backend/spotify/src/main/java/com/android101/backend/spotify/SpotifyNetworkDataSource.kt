package com.android101.backend.spotify

import com.android101.backend.spotify.dto.GetPlaylistResponse
import com.android101.backend.spotify.dto.TokenResponseDto
import com.slack.eithernet.ApiResult
import javax.inject.Inject

class SpotifyNetworkDataSource @Inject constructor(
    private val api: SpotifyApi,
) {
    suspend fun getToken(): ApiResult<TokenResponseDto, Throwable> =
        api.getToken(
            url = "https://accounts.spotify.com/api/token",
        )

    suspend fun getTopMusics(token: String): ApiResult<GetPlaylistResponse, Throwable> =
        api.getTopMusics("Bearer $token")
}
