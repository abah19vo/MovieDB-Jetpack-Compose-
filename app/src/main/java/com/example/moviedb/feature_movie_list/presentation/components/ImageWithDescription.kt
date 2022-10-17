package com.example.moviedb.feature_movie_list.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Image_with_description(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "Image_with_description")
    }
}

@Preview(name = "Image_with_description")
@Composable
private fun PreviewImage_with_description() {
    Image_with_description()
}