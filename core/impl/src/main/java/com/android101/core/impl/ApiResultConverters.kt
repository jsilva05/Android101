package com.android101.core.impl

import com.slack.eithernet.ApiResult
import org.mobilenativefoundation.store.store5.FetcherResult

/**
 * Transforms an [ApiResult] into a [FetcherResult]
 */
fun <T : Any> ApiResult<T, Throwable>.toFetcherResult(
    errorMapper: ((ApiResult.Failure<*>, Throwable) -> Throwable)? = null,
): FetcherResult<T> {
    return when (this) {
        is ApiResult.Failure -> {
            val cause = error()
            val throwable = errorMapper?.invoke(this, cause) ?: cause
            FetcherResult.Error.Exception(throwable)
        }

        is ApiResult.Success -> FetcherResult.Data(value)
    }
}

/**
 * Extracts a [Throwable] from an [ApiResult.Failure]
 */
private fun <E : Throwable> ApiResult.Failure<E>.error(): Throwable {
    return when (this) {
        is ApiResult.Failure.ApiFailure -> error ?: Throwable("Api Failure")
        is ApiResult.Failure.HttpFailure -> error ?: Throwable("HTTP Failure $code")
        is ApiResult.Failure.NetworkFailure -> error
        is ApiResult.Failure.UnknownFailure -> error
    }
}
