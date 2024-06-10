package com.android101.backend.spotify

import com.android101.backend.spotify.dto.GetPlaylistResponse
import com.slack.eithernet.ApiResult
import javax.inject.Inject

class SpotifyNetworkDataSource @Inject constructor(
    private val api: SpotifyApi,
) {
    suspend fun getTopMusics(): ApiResult<GetPlaylistResponse, Throwable> =
        api.getTopMusics()
}
