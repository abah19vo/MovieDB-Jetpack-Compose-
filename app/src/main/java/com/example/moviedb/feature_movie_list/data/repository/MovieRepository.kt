package com.example.moviedb.feature_movie_list.data.repository

import com.example.moviedb.feature_movie_list.data.api_services.MovieServiceInstance
import com.example.moviedb.feature_movie_list.domain.model.MovieDetails
import com.example.moviedb.feature_movie_list.domain.model.MovieList
import com.example.moviedb.feature_movie_list.domain.util.CustomException
import com.example.moviedb.feature_movie_list.presentation.util.Resource

class MovieRepository : IMovieRepository{
    override suspend fun getMovieList(pageNumber:Int) : Resource<MovieList>{
        val response = MovieServiceInstance.api.getMovieList(pageNumber = pageNumber)
        if(!response.isSuccessful) throw CustomException.UnSuccessfulRequest()
        else response.body()?.let { movieList->
            if(movieList.results.isEmpty()) throw CustomException.NoContent()
            return Resource.Success(movieList)
        }
            ?:throw CustomException.UnexpectedError()
    }
    override suspend fun getMovieById(movieId : Int): Resource<MovieDetails>{
        val response = MovieServiceInstance.api.getMovieDetails(movieId)
        if(!response.isSuccessful) throw CustomException.UnSuccessfulRequest(response.message())
        if(response.body() == null) throw CustomException.NoContent()
        else response.body()?.let { movie ->
            return Resource.Success(movie)
        }?: throw CustomException.UnexpectedError()


    }
}