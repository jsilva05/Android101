package com.android101.feature.authentication

import com.android101.backend.spotify.SpotifyNetworkDataSource
import com.android101.core.impl.toFetcherResult
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.StoreBuilder

@Module
@InstallIn(SingletonComponent::class)
internal object AuthenticationTokenStoreModule {
    @Provides
    fun provideAuthenticationTokenStore(
        spotifyNetworkDataSource: SpotifyNetworkDataSource,
        authenticationTokenPreferencesDataSource: AuthenticationTokenPreferencesDataSource,
    ): Store<Unit, AuthenticationToken> =
        StoreBuilder.from(
            fetcher = Fetcher.ofResult { _: Unit ->
                spotifyNetworkDataSource
                    .getToken()
                    .toFetcherResult()
            },
            sourceOfTruth = SourceOfTruth.of(
                reader = {
                    authenticationTokenPreferencesDataSource.getToken()
                },
                writer = { _: Unit, response ->
                    authenticationTokenPreferencesDataSource.setToken(
                        token = response.accessToken,
                        expiresIn = response.expiresIn,
                    )
                },
            ),
        ).build()
}
