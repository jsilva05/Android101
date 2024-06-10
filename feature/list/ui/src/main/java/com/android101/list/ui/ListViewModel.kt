package com.android101.list.ui

import androidx.compose.runtime.Composable
import com.android101.ui.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

sealed interface ListEvent {
    data class SelectMusic(val id: String) : ListEvent
}

data class ListModel(
    val loading: Boolean,
    val musics: List<MusicUiModel>,
)

@HiltViewModel
class ListViewModel @Inject constructor() : BaseViewModel<ListEvent, ListModel>() {
    @Composable
    override fun models(events: Flow<ListEvent>): ListModel =
        listScreenPresenter(events)
}
