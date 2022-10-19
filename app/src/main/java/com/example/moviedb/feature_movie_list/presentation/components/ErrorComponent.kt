package com.example.moviedb.feature_movie_list.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviedb.ui.theme.MovieDBTheme
import com.example.moviedb.ui.theme.typography

@Composable
fun ErrorComponent(
    modifier: Modifier = Modifier,
    text:String= "",
    buttonText:String="Refresh",
    buttonAction: () -> Unit,
) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement= Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            Icons.Rounded.Warning ,
            contentDescription = "",
            modifier = Modifier.size(150.dp)
        )
        Text(text = text,
            style = typography.h1,
            modifier = Modifier
                .widthIn(100.dp, 300.dp)
                .align(Alignment.CenterHorizontally),
            textAlign= TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = buttonAction) {
            Text(buttonText )
        }
    }
}

@Preview(name = "ErrorComponent", showBackground = true)
@Composable
private fun PreviewErrorComponent() {
    MovieDBTheme() {
        ErrorComponent(text="Error Component dakjs ndkaj", buttonAction = {})
    }

}