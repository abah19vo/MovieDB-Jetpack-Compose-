package com.example.moviedb.feature_movie_list.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviedb.ui.theme.MovieDBTheme
import com.example.moviedb.ui.theme.Shapes
import com.example.moviedb.ui.theme.typography

@Composable
fun TextCard(
    modifier: Modifier = Modifier,
    title:String = "",
    subTitle: String = "",
    verticalArrangement:  Arrangement.HorizontalOrVertical = Arrangement.SpaceBetween,
) {
    Card(
        modifier.size(
            150.dp,
            75.dp
        ),
        elevation = 1.dp,
        shape = Shapes.small
    ) {

        Column(
            verticalArrangement = verticalArrangement,
        ) {

            Text(
                modifier = Modifier.padding(
                    start = 4.dp,
                    bottom = 3.dp),
                text = title,
                style = typography.h2,
            )


            Text(
                text = subTitle, style = typography.h1,
                modifier= Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 4.dp)
            )
        }


    }
}

@Preview(name = "TextCard", showBackground = false)
@Composable
private fun PreviewTextCard() {
    MovieDBTheme {
        TextCard(
            title = "Votes",
            subTitle = "560",
            verticalArrangement = Arrangement.SpaceBetween
        )
    }

}