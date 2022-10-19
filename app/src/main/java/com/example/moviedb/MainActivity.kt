package com.example.moviedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviedb.feature_movie_list.presentation.movie_details.MovieDetailView
import com.example.moviedb.feature_movie_list.presentation.movies.MovieListView
import com.example.moviedb.feature_movie_list.presentation.util.Screen
import com.example.moviedb.ui.theme.MovieDBTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovieDBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MovieListView.route
                    ) {
                        composable(
                            route = Screen.MovieListView.route
                        ){
                            MovieListView(
                                navController = navController
                            )
                        }
                        composable(
                            route = Screen.MovieDetailsView.route+
                                    "?movieId={movieId}",
                            arguments = listOf(
                                navArgument(
                                    name = "movieId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },

                            )
                        ){
                            MovieDetailView(
                                navController = navController,
                                movieId = it.arguments?.getInt("movieId"),
                                )
                        }
                    }
                }
            }
        }
    }
}

