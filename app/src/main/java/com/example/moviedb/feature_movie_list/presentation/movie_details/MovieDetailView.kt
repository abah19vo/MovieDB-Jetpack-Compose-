package com.example.moviedb.feature_movie_list.presentation.movie_details


import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.moviedb.ui.theme.MovieDBTheme


@Composable
fun MovieDetailView(
    movieId: Int?,
    navController: NavController = rememberNavController(),
    viewModel: MovieDetailViewModel= hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()



    Text(text = "Hello $movieId!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MovieDBTheme {
        //MovieDetailView(123);
    }
}