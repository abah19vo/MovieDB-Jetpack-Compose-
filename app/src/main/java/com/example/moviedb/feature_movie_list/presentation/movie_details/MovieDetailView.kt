package com.example.moviedb.feature_movie_list.presentation.movie_details


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moviedb.feature_movie_list.domain.model.MovieDetails
import com.example.moviedb.feature_movie_list.domain.util.ImageSize
import com.example.moviedb.feature_movie_list.presentation.components.ErrorComponent
import com.example.moviedb.feature_movie_list.presentation.components.Loading
import com.example.moviedb.feature_movie_list.presentation.util.Resource


@Composable
fun MovieDetailView(
    movieId: Int?,
    navController: NavController = rememberNavController(),
    viewModel: MovieDetailViewModel= hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    viewModel.getMovieDetails(movieId)
    val state by viewModel.movieState.collectAsState()
    Scaffold(
        scaffoldState = scaffoldState,
    ) {
        Column(
            modifier = Modifier
                .padding(it),
        ) {
            when (state) {
                is Resource.Success<MovieDetails> -> state.data?.let { movieDetails ->
                    MovieDetailsBody(
                        getImagePath = { path: String, size: ImageSize ->
                            viewModel.getImagePath(
                                path,
                                size
                            )
                        },
                        movieDetails
                    )
                }
                is Resource.Error -> state.message?.let { msg ->
                    ErrorComponent(
                        text = msg,
                        buttonAction = {
                            viewModel.refresh(movieId)
                        }
                    )
                }
                is Resource.Loading -> Loading()
            }
        }
    }
}


