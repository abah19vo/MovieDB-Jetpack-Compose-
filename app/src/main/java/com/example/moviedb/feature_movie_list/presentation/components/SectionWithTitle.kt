package com.example.moviedb.feature_movie_list.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LazyRowTitle(
    modifier: Modifier = Modifier,
    title:String ="",
    componets:  Unit
) {
    Column() {
        Text(text = "Spoken Languages")
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow {
            componets
        }

        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Preview(name = "LazyRowTitle")
@Composable
private fun PreviewLazyRowTitle() {
    LazyRowTitle()
}