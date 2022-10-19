package com.example.moviedb.feature_movie_list.presentation.movie_details

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MovieDetailBody(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "MovieDetailBody")
    }
}

@Preview(name = "MovieDetailBody")
@Composable
private fun PreviewMovieDetailBody() {
    MovieDetailBody()
}