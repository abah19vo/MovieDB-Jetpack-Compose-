package com.example.moviedb.feature_movie_list.presentation.movies

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.moviedb.ui.theme.MovieDBTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moviedb.R
import com.example.moviedb.feature_movie_list.domain.model.MovieList
import com.example.moviedb.feature_movie_list.presentation.components.ErrorComponent
import com.example.moviedb.feature_movie_list.presentation.components.ImageWithDescription
import com.example.moviedb.feature_movie_list.presentation.components.Loading
import com.example.moviedb.feature_movie_list.presentation.util.Resource
import com.example.moviedb.feature_movie_list.presentation.util.Screen
import com.example.moviedb.ui.theme.Shapes
import com.example.moviedb.ui.theme.gray800
import org.intellij.lang.annotations.JdkConstants


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
        content = { it ->
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
                    is Resource.Error -> ErrorComponent()
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
