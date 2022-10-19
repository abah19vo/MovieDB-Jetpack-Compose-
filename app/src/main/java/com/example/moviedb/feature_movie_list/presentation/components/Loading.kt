package com.example.moviedb.feature_movie_list.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviedb.ui.theme.MovieDBTheme

@Composable
fun Loading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier.fillMaxSize(),
        Alignment.Center,

    ) {
        CircularProgressIndicator(
            strokeWidth = 6.dp,
            modifier = Modifier
                .size(100.dp)
        )
    }
}

@Preview(name = "Loading")
@Composable
private fun PreviewLoading() {
    MovieDBTheme() {
        Loading()
    }

}