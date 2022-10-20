package com.example.moviedb.feature_movie_list.presentation.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviedb.feature_movie_list.domain.model.MovieList
import com.example.moviedb.feature_movie_list.presentation.components.ImageWithDescription
import com.example.moviedb.feature_movie_list.presentation.util.Screen

@Composable
fun MovieListBody(
    navController: NavController,
    getImagePath: (path:String)->String,
    movieList: MovieList
){

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(0.dp,20.dp),

        ){

        items(movieList.results){movie->
            ImageWithDescription(
                modifier = Modifier.padding(bottom = 10.dp)
                    .clickable {
                        navController.navigate(Screen.MovieDetailsView.route+"?movieId=${movie.id}")
                    },
                imageModel= getImagePath(movie.posterPath),
                text = movie.title,
                subText = movie.releaseDate,
            )
        }
    }

}
