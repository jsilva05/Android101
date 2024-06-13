package com.android101.feature.authentication

import app.cash.quiver.Failure
import app.cash.quiver.Outcome
import app.cash.quiver.Present
import co.touchlab.kermit.Logger
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.impl.extensions.fresh
import org.mobilenativefoundation.store.store5.impl.extensions.get
import java.time.Clock
import java.time.LocalDateTime
import javax.inject.Inject

@Suppress("TooGenericExceptionCaught")
internal class GetAuthenticationTokenImpl @Inject constructor(
    private val store: Store<Unit, AuthenticationToken>,
    private val clock: Clock,
) : GetAuthenticationToken {
    override suspend fun invoke(): Outcome<GetAuthenticationToken.Error, AuthenticationToken> {
        try {
            val cachedToken = store.get(Unit)
            if (!cachedToken.isExpired()) {
                return Present(cachedToken)
            }
        } catch (ex: Throwable) {
            Logger.e(throwable = ex) { "An error occurred fetching the token" }
        }

        return try {
            Present(store.fresh(Unit))
        } catch (ex: Throwable) {
            Failure(GetAuthenticationToken.Error(ex))
        }
    }

    private fun AuthenticationToken.isExpired(): Boolean {
        return LocalDateTime.now(clock).isAfter(expiresAt)
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class GetAuthenticationTokenModule {
    @Binds
    abstract fun bind(impl: GetAuthenticationTokenImpl): GetAuthenticationToken
}
