package com.android101.feature.list

import app.cash.quiver.Absent
import app.cash.quiver.Failure
import app.cash.quiver.Outcome
import app.cash.quiver.fold
import com.android101.core.impl.toOutcome
import com.android101.feature.authentication.AuthenticationToken
import com.android101.feature.authentication.GetAuthenticationToken
import com.android101.feature.authentication.Token
import com.android101.list.GetTopTracks
import com.android101.list.GetTopTracks.Error
import com.android101.list.model.Track
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.StoreReadRequest
import javax.inject.Inject

internal class GetTopTracksImpl @Inject constructor(
    private val store: Store<Token, List<Track>>,
    private val getAuthenticationToken: GetAuthenticationToken,
) : GetTopTracks {
    override fun invoke(): Flow<Outcome<Error, List<Track>>> = flow {
        val token = getAuthenticationToken()
        token.fold(
            onPresent = {
                emitAll(onPresent(it))
            },
            onFailure = {
                emit(Failure(Error.Authentication(it.cause)))
            },
            onAbsent = {
                emit(Absent)
            },
        )
    }

    private fun onPresent(authenticationToken: AuthenticationToken): Flow<Outcome<Error, List<Track>>> {
        return store.stream(
            StoreReadRequest.cached(
                key = authenticationToken.token,
                refresh = true,
            ),
        ).map { response ->
            response.toOutcome { Error.Generic(it) }
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class GetTopTracksModule {
    @Binds
    abstract fun bind(impl: GetTopTracksImpl): GetTopTracks
}
