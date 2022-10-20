package com.example.moviedb.feature_movie_list.data.repository

import com.example.moviedb.feature_movie_list.domain.model.MovieDetails
import com.example.moviedb.feature_movie_list.domain.model.MovieList
import retrofit2.Response

interface IMovieRepository {
    suspend fun getMovieList(pageNumber:Int = 1) : Response<MovieList>
    suspend fun getMovieById(movieId : Int) : Response<MovieDetails>
}