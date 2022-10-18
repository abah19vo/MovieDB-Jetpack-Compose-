package com.example.moviedb.feature_movie_list.presentation.movie_details


import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Black
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.moviedb.feature_movie_list.domain.model.MovieDetails
import com.example.moviedb.feature_movie_list.domain.util.ImageSize
import com.example.moviedb.feature_movie_list.presentation.components.ErrorComponent
import com.example.moviedb.feature_movie_list.presentation.components.ImageWithDescription
import com.example.moviedb.feature_movie_list.presentation.components.Loading
import com.example.moviedb.feature_movie_list.presentation.util.Resource
import com.example.moviedb.ui.theme.MovieDBTheme
import com.example.moviedb.ui.theme.Shapes
import com.example.moviedb.ui.theme.white150
import java.util.*


@Composable
fun MovieDetailView(
    movieId: Int?,
    navController: NavController = rememberNavController(),
    viewModel: MovieDetailViewModel= hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    viewModel.getMovieDetails(movieId)
    val state by viewModel.movieState.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        content = {
            Column(
                modifier = Modifier
                    .padding(it),
            ){

                when(state){
                    is Resource.Success<MovieDetails> -> state.data?.let { movieDetails->
                        MovieDetailsBody(
                            viewModel,
                            movieDetails
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
fun MovieDetailsBody(
    viewModel: MovieDetailViewModel = hiltViewModel(),
    movieDetails: MovieDetails,
) {
    MovieDBTheme {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())

        ){


            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(300.dp, 600.dp)
            ) {
                ImageBox(
                    viewModel.getImagePath( movieDetails.posterPath),
                    viewModel.getImagePath( movieDetails.backdropPath),
                )
            }
            Column(
                Modifier.padding(
                    vertical = 10.dp,
                    horizontal = 5.dp
                )
            ) {
                Text(
                    text = movieDetails.title,
                    style = MaterialTheme.typography.h1.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                )
                Text(
                    text = movieDetails.overview,
                    style = MaterialTheme.typography.body1,
                )
                Text(text = movieDetails.voteAverage.toString())
                Text(text = movieDetails.voteCount.toString())
                Text(text = movieDetails.budget.toString())
                Text(text = movieDetails.releaseDate.toString())
                Text(text = "Production companies")
            }

            Spacer(modifier = Modifier.height(50.dp))


            LazyRow(
                verticalAlignment = Alignment.Bottom,

                modifier = Modifier.height(50.dp)
            ){
                items(movieDetails.productionCompanies){ company ->

                    ImageWithDescription(
                        shape = Shapes.large,
                        imageModel = viewModel.getImagePath(company.logoPath, ImageSize.SMALL),
                        text = company.name,
                        subText = company.originCountry

                    )
                }
            }

            LazyRow(){
                items(movieDetails.productionCountries){ company ->
                    ImageWithDescription(
                        text = company.name,
                        subText = company.iso31661,
                    )
                }
            }
            LazyRow(){
                items(movieDetails.genres){ item ->
                    Text(text = item.name)
                }
            }
        }

    }
}

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
                    radius= 5.dp,
                ).background(color = white150),
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
                ),
        )


    }

}