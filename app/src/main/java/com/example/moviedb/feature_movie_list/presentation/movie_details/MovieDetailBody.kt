package com.example.moviedb.feature_movie_list.presentation.movie_details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.moviedb.feature_movie_list.domain.model.MovieDetails
import com.example.moviedb.feature_movie_list.domain.util.ImageSize
import com.example.moviedb.feature_movie_list.presentation.components.ImageBox
import com.example.moviedb.feature_movie_list.presentation.components.ImageWithDescription
import com.example.moviedb.feature_movie_list.presentation.components.SectionWithTitle
import com.example.moviedb.feature_movie_list.presentation.components.TextCard
import com.example.moviedb.ui.theme.MovieDBTheme
import com.example.moviedb.ui.theme.Shapes


@Composable
fun MovieDetailsBody(
    getImagePath: (path:String, size: ImageSize)->String,
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
                    getImagePath( movieDetails.posterPath, ImageSize.LARGE),
                    getImagePath( movieDetails.backdropPath, ImageSize.LARGE),
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
                    ),
                )
                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    text = movieDetails.overview,
                    style = MaterialTheme.typography.body1,
                )

            }

            Spacer(modifier = Modifier.height(25.dp))

            LazyRow(
                contentPadding= PaddingValues(1.dp)
            ) {

                item{
                    Box(Modifier.padding(horizontal = 20.dp)) {

                        TextCard(
                            title = "votes",
                            subTitle = movieDetails.voteCount.toString()
                        )
                    }
                }
                item{
                    Box(Modifier.padding(horizontal = 20.dp)) {
                        TextCard(
                            title = "vote Average",
                            subTitle = movieDetails.voteAverage.toString()
                        )
                    }

                }
                item{
                    Box(Modifier.padding(horizontal = 20.dp)) {
                        TextCard(
                            title = "budget",
                            subTitle = movieDetails.budget.toString()
                        )
                    }

                }
                item{
                    Box(Modifier.padding(horizontal = 20.dp)) {
                        TextCard(
                            title = "revenue",
                            subTitle = movieDetails.revenue.toString()
                        )
                    }

                }
                item{
                    Box(Modifier.padding(horizontal = 20.dp)) {
                        TextCard(
                            title = "status",
                            subTitle = movieDetails.status
                        )
                    }

                }

            }

            Spacer(modifier = Modifier.height(50.dp))

            SectionWithTitle(
                title = "Production companies",
                content = {
                    LazyRow(
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier.heightIn(50.dp,200.dp),
                    ){
                        items(movieDetails.productionCompanies){ company ->
                            ImageWithDescription(
                                shape = Shapes.large,
                                imageModel = company.logoPath?.let { getImagePath(it, ImageSize.SMALL) },
                                text = company.name,
                                subText = company.originCountry

                            )

                        }
                    }
                }
            )

            SectionWithTitle(
                title ="Production countries",
                content = {
                    LazyRow {
                        items(movieDetails.productionCountries){ company ->
                            Box(Modifier.padding(horizontal = 10.dp)) {

                                TextCard(
                                    title = company.name,
                                    subTitle = company.iso31661,
                                )
                            }
                        }
                    }
                }
            )

            SectionWithTitle(
                title = "Genres",
                content = {
                    LazyRow {
                        items(movieDetails.genres){ item ->
                            Box(
                                Modifier.padding(horizontal = 10.dp)
                            ) {

                                TextCard(
                                    subTitle = item.name,
                                    verticalArrangement = Arrangement.Center
                                )
                            }
                        }
                    }
                }
            )

            SectionWithTitle(
                title ="Spoken Languages",
                content = {
                    LazyRow {
                        items(movieDetails.spokenLanguages){ item ->
                            Box(Modifier.padding(horizontal = 10.dp)) {
                                TextCard(
                                    subTitle = item.name,
                                    verticalArrangement = Arrangement.Center
                                )
                            }
                        }
                    }
                }
            )
        }

    }
}

