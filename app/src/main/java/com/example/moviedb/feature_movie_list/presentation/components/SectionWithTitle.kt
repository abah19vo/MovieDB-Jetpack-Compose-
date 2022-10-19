package com.example.moviedb.feature_movie_list.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviedb.ui.theme.MovieDBTheme
import com.example.moviedb.ui.theme.typography

@Composable
fun SectionWithTitle(
    modifier: Modifier = Modifier,
    title:String ="",
    content:  @Composable () -> Unit
) {
    Column(modifier) {
        Text(title, style = typography.body1 )
        Spacer(modifier = Modifier.height(20.dp))
        content()
        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Preview(name = "LazyRowTitle", showBackground = true)
@Composable
private fun PreviewLazyRowTitle() {
    SectionWithTitle(
        title = "hello",

    ){
        MovieDBTheme() {
            TextCard(
                title = "Title",
                subTitle = "Content"
            )
        }

    }
}