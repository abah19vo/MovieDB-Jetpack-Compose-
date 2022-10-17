package com.example.moviedb.feature_movie_list.presentation.util

sealed class Screen(val route: String) {
    object MovieListView: Screen("movie_list_view")
    object MovieDetailsView: Screen("movie_details_view")
}