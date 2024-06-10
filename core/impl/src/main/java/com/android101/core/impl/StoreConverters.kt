package com.android101.core.impl

import app.cash.quiver.Absent
import app.cash.quiver.Failure
import app.cash.quiver.Outcome
import app.cash.quiver.Present
import org.mobilenativefoundation.store.store5.StoreReadResponse

fun <Value : Any, Error : Any> StoreReadResponse<Value>.toOutcome(
    failureCreator: (throwable: Throwable) -> Error,
): Outcome<Error, Value> {
    return when (this) {
        is StoreReadResponse.Data -> Present(value)
        is StoreReadResponse.Error.Exception -> Failure(failureCreator(error))
        StoreReadResponse.Initial,
        is StoreReadResponse.Loading,
        is StoreReadResponse.NoNewData,
        -> Absent

        is StoreReadResponse.Error.Custom<*>,
        is StoreReadResponse.Error.Message,
        -> error("Custom errors are not supported")
    }
}
