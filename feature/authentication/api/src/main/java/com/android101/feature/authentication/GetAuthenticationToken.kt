package com.android101.feature.authentication

import app.cash.quiver.Outcome

/**
 * Returns the cached authentication token.
 * If it's not present, it requests a new one
 */
interface GetAuthenticationToken {
    suspend operator fun invoke(): Outcome<Error, AuthenticationToken>

    data class Error(
        val cause: Throwable,
    )
}
