package com.example.moviedb.feature_movie_list.presentation.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moviedb.feature_movie_list.domain.model.MovieList
import com.example.moviedb.feature_movie_list.presentation.components.ErrorComponent
import com.example.moviedb.feature_movie_list.presentation.components.ImageWithDescription
import com.example.moviedb.feature_movie_list.presentation.components.Loading
import com.example.moviedb.feature_movie_list.presentation.util.Resource
import com.example.moviedb.feature_movie_list.presentation.util.Screen


@Composable
fun MovieListView(
    navController: NavController = rememberNavController(),
    viewModel: MovieListViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val state by viewModel.movieState.collectAsState()
    Scaffold(
        scaffoldState = scaffoldState,
        content = {
            Column(
            modifier = Modifier
                .padding(it),
            ){

                when(state){
                    is Resource.Success<MovieList> -> state.data?.let { movielist->
                        MovieListBody(
                            navController,
                            viewModel,
                            movielist,
                        )
                    }
                    is Resource.Error -> state.message?.let { it1 -> ErrorComponent(
                        text= it1,
                        buttonAction = {
                            viewModel.refresh()
                        }
                    ) }
                    is Resource.Loading -> Loading()
                }
            }
        },

   )
}
@Composable
fun MovieListBody(
    navController: NavController ,
    viewModel: MovieListViewModel ,
    movieList:MovieList
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
                imageModel= viewModel.getImagePath(movie.posterPath),
                text = movie.title,
                subText = movie.releaseDate,

            )
        }
    }

}
