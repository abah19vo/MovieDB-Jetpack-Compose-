package com.example.moviedb.feature_movie_list.data.repository

import com.example.moviedb.feature_movie_list.data.api_services.MovieServiceInstance

class MovieRepository : IMovieRepository{
    override suspend fun getMovieList(pageNumber:Int)
            = MovieServiceInstance.api.getMovieList(pageNumber = pageNumber)
    override suspend fun getMovieById(movieId : Int)
            =  MovieServiceInstance.api.getMovieDetails(movieId)
}