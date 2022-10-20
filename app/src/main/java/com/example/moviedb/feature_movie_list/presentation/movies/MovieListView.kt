package com.example.moviedb.feature_movie_list.presentation.movies

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
import com.example.moviedb.feature_movie_list.domain.model.MovieList
import com.example.moviedb.feature_movie_list.presentation.components.ErrorComponent
import com.example.moviedb.feature_movie_list.presentation.components.Loading
import com.example.moviedb.feature_movie_list.presentation.util.Resource


@Composable
fun MovieListView(
    navController: NavController = rememberNavController(),
    viewModel: MovieListViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    val state by viewModel.movieState.collectAsState()
    Scaffold(
        scaffoldState = scaffoldState,
        content = {
            Column(
                modifier = Modifier
                    .padding(it),
            ) {

                when (state) {
                    is Resource.Success<MovieList> -> state.data?.let { movieList ->
                        MovieListBody(
                            navController,
                            { path:String -> viewModel.getImagePath(path) },
                            movieList,
                        )
                    }
                    is Resource.Error -> state.message?.let { it1 ->
                        ErrorComponent(
                            text = it1,
                            buttonAction = {
                                viewModel.refresh()
                            }
                        )
                    }
                    is Resource.Loading -> Loading()
                }
            }
        },
    )
}
