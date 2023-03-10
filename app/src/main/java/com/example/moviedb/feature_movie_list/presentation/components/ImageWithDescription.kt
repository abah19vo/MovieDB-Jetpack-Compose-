package com.example.moviedb.feature_movie_list.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviedb.R
import com.example.moviedb.ui.theme.LightColorPalette
import com.example.moviedb.ui.theme.Shapes

@Composable
fun ImageWithDescription(
    modifier: Modifier = Modifier,
    shape : Shape = Shapes.small,
    imageModel: String?=null,
    text: String,
    subText: String,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier

    ) {
        if(imageModel!=null){
            AsyncImage(
                model = imageModel,
                contentDescription = "",
                modifier = Modifier
                    .clip(shape = shape)
                    .background(LightColorPalette.onPrimary).padding(1.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource( R.drawable.ic_launcher_background),
            )
        }


        Column(
            modifier = Modifier
                .padding(
                    top = 5.dp,
                    start = 5.dp,
                )
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .width(175.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,

                )
            Text(
                text = subText,
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .width(150.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
            )
            

        }
    }

}

