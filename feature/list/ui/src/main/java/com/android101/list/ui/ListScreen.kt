package com.android101.list.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android101.ui.shared.Loading

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    viewModel: ListViewModel = viewModel(),
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
    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (model.loading) {
            Loading(
                modifier = Modifier.size(128.dp),
            )
            return
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(
                items = model.musics,
                key = { music -> music.id },
            ) { message ->
                MusicRow(message, onEvent)
            }
        }
    }
}

@Composable
private fun MusicRow(
    music: MusicUiModel,
    onEvent: (ListEvent) -> Unit,
) {
    Text(
        text = music.title,
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
        loading = false,
        musics = listOf(
            MusicUiModel("1", "This is the time"),
            MusicUiModel("2", "Now or never"),
            MusicUiModel("3", "Big bass knuckles"),
        ),
    )

    List(
        model = model,
        onEvent = {},
    )
}

@PreviewScreenSizes
@Composable
private fun PreviewLoadingList() {
    val model = ListModel(
        loading = true,
        musics = emptyList(),
    )

    List(
        model = model,
        onEvent = {},
    )
}
