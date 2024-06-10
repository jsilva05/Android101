package com.android101.ui.shared

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.cash.quiver.Absent
import app.cash.quiver.Failure
import app.cash.quiver.Outcome
import app.cash.quiver.Present

@Composable
fun <T, E> OutcomeContent(
    outcome: Outcome<E, T>,
    modifier: Modifier = Modifier,
    loadingSize: Dp = 64.dp,
    content: @Composable AnimatedContentScope.(T) -> Unit,
) = AnimatedContent(
    targetState = outcome,
    contentKey = { it::class },
    modifier = modifier,
    contentAlignment = Alignment.Center,
    transitionSpec = { fadeIn() togetherWith fadeOut() using null },
    label = "OutcomeContent",
) {
    when (it) {
        Absent -> Loading(loadingSize = loadingSize)
        is Failure -> Error()
        is Present -> content(it.value)
    }
}

@PreviewScreenSizes
@Composable
private fun PreviewOutcomeContentLoading() {
    OutcomeContent(Absent) { }
}

@PreviewScreenSizes
@Composable
private fun PreviewOutcomeContentError() {
    OutcomeContent(Failure(IllegalStateException("error"))) { }
}
