package com.android101.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Loading(
    loadingSize: Dp,
    modifier: Modifier = Modifier,
) {
    Box(modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(loadingSize)
                .align(BiasAlignment(0f, -0.4f)),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@PreviewScreenSizes
@Composable
private fun PreviewLoading() {
    Loading(128.dp)
}
