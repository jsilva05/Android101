package com.android101.list.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.cash.quiver.Absent
import app.cash.quiver.Outcome
import com.android101.list.GetTopTracks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun listScreenPresenter(
    events: Flow<ListEvent>,
    getTopTracks: GetTopTracks,
): ListModel {
    var musics: Outcome<GetTopTracks.Error, List<TrackUiModel>> by remember { mutableStateOf(Absent) }

    LaunchedEffect(Unit) {
        launch(Dispatchers.Default) {
            getTopTracks().collectLatest { outcome ->
                // If we already have data, we want to ignore errors afterwards
                if (musics.isPresent() && (outcome.isFailure() || outcome.isAbsent())) {
                    return@collectLatest
                }

                musics = outcome.map { trackList ->
                    trackList.map { it.toUiModel() }
                }
            }
        }
    }

    UiEvents(events)

    return ListModel(
        tracks = musics,
    )
}

@Composable
private fun UiEvents(events: Flow<ListEvent>) {
    LaunchedEffect(Unit) {
        events.collect { event ->
            when (event) {
                is ListEvent.SelectMusic -> TODO()
            }
        }
    }
}
