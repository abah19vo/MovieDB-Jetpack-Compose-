package com.example.moviedb.feature_movie_list.data.repository

import com.example.moviedb.feature_movie_list.domain.model.MovieDetails
import com.example.moviedb.feature_movie_list.domain.model.MovieList
import com.example.moviedb.feature_movie_list.presentation.util.Resource

interface IMovieRepository {
    suspend fun getMovieList(pageNumber:Int = 1) : Resource<MovieList>
    suspend fun getMovieById(movieId : Int) : Resource<MovieDetails>
}