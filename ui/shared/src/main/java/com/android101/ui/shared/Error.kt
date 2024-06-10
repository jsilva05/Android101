package com.android101.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewScreenSizes

@Composable
fun Error(
    modifier: Modifier = Modifier,
) {
    Box(modifier.fillMaxSize()) {
        Text(
            text = "Something went wrong",
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(BiasAlignment(0f, -0.4f)),
        )
    }
}

@PreviewScreenSizes
@Composable
private fun PreviewError() {
    Error()
}
