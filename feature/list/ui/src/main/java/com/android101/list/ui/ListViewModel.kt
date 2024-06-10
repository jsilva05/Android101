package com.android101.list.ui

import androidx.compose.runtime.Composable
import app.cash.quiver.Outcome
import com.android101.list.GetMusicList
import com.android101.ui.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

sealed interface ListEvent {
    data class SelectMusic(val id: String) : ListEvent
}

data class ListModel(
    val tracks: Outcome<GetMusicList.Error, List<TrackUiModel>>,
)

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getMusicList: GetMusicList,
) : BaseViewModel<ListEvent, ListModel>() {
    @Composable
    override fun models(events: Flow<ListEvent>): ListModel =
        listScreenPresenter(events, getMusicList)
}
