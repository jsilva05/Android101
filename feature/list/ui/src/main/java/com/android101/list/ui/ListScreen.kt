package com.android101.list.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.cash.quiver.Present
import com.android101.ui.shared.OutcomeContent

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    viewModel: ListViewModel = hiltViewModel(),
) {
    val model by viewModel.models.collectAsState()
    List(
        model = model,
        onEvent = viewModel::emit,
        modifier = modifier,
    )
}

@Composable
private fun List(
    model: ListModel,
    onEvent: (ListEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutcomeContent(
        outcome = model.tracks,
        modifier = modifier.fillMaxSize(),
    ) { tracks ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(
                items = tracks,
                key = { track -> track.id },
            ) { message ->
                MusicRow(message, onEvent)
            }
        }
    }
}

@Composable
private fun MusicRow(
    music: TrackUiModel,
    onEvent: (ListEvent) -> Unit,
) {
    Text(
        text = music.name,
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                onEvent(ListEvent.SelectMusic(music.id))
            },
    )
}

@PreviewScreenSizes
@Composable
private fun PreviewList() {
    val model = ListModel(
        tracks = Present(
            listOf(
                TrackUiModel("1", "This is the time"),
                TrackUiModel("2", "Now or never"),
                TrackUiModel("3", "Big bass knuckles"),
            ),
        ),
    )

    List(
        model = model,
        onEvent = {},
    )
}
