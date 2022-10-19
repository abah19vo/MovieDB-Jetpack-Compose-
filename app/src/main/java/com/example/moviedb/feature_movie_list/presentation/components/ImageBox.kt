package com.example.moviedb.feature_movie_list.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviedb.ui.theme.white150

@Composable
fun ImageBox(
    backgroundImagePath:String,
    frontImagePath:String,
){
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center,

        ){

        AsyncImage(
            model =  frontImagePath,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .blur(
                    radius = 5.dp,
                )
                .background(color = white150),
            contentScale = ContentScale.Crop,
        )
        AsyncImage(
            model = backgroundImagePath ,
            contentDescription = "",
            alignment = Alignment.Center,
            modifier = Modifier
                .width(300.dp)
                .padding(
                    horizontal = 3.dp,
                )
                .blur(
                    0.dp,
                    edgeTreatment = BlurredEdgeTreatment.Unbounded
                ),
        )


    }

}