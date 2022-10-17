package com.example.moviedb.feature_movie_list.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ErrorComponent(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "ErrorComponent")
    }
}

@Preview(name = "ErrorComponent")
@Composable
private fun PreviewErrorComponent() {
    ErrorComponent()
}