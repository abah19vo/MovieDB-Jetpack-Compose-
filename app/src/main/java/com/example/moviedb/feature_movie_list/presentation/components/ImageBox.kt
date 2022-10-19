package com.example.moviedb.feature_movie_list.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ImageBox(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "ImageBox")
    }
}

@Preview(name = "ImageBox")
@Composable
private fun PreviewImageBox() {
    ImageBox()
}