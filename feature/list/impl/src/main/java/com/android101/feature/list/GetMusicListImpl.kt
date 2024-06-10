package com.android101.feature.list

import app.cash.quiver.Outcome
import com.android101.core.impl.toOutcome
import com.android101.list.GetMusicList
import com.android101.list.GetMusicList.Error
import com.android101.list.model.Track
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.StoreReadRequest
import javax.inject.Inject

internal class GetMusicListImpl @Inject constructor(
    private val store: Store<Unit, List<Track>>,
) : GetMusicList {
    override fun invoke(): Flow<Outcome<Error, List<Track>>> =
        store.stream(
            StoreReadRequest.cached(
                key = Unit,
                refresh = true,
            ),
        ).map { response ->
            response.toOutcome { Error(it) }
        }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class GetMusicListModule {
    @Binds
    abstract fun bind(impl: GetMusicListImpl): GetMusicList
}
