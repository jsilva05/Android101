package com.android101.list.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

@Composable
fun listScreenPresenter(events: Flow<ListEvent>): ListModel {
    var musics: List<MusicUiModel> by remember { mutableStateOf(emptyList()) }

    LaunchedEffect(Unit) {
        delay(5000L)
        musics = listOf(
            MusicUiModel("1", "This is the time"),
            MusicUiModel("2", "Now or never"),
            MusicUiModel("3", "Big bass knuckles"),
        )
    }

    UiEvents(events)

    return ListModel(
        loading = musics.isEmpty(),
        musics = musics,
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
