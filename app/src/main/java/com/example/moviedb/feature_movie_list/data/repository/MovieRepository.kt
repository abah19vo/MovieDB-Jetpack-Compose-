package com.example.moviedb.feature_movie_list.data.repository

import com.example.moviedb.feature_movie_list.data.api_services.MovieServiceInstance

class MovieRepository {

    suspend fun getMovieList(pageNumber:Int = 1) = MovieServiceInstance.api.getMovieList(pageNumber = pageNumber)

    suspend fun getMovieById(movieId : Int) =  MovieServiceInstance.api.getMovieDetails(movieId)

}