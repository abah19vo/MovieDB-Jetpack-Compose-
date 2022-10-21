package com.example.moviedb.feature_movie_list.data.repository

import com.example.moviedb.feature_movie_list.domain.model.MovieDetails
import com.example.moviedb.feature_movie_list.domain.model.MovieList
import com.example.moviedb.feature_movie_list.domain.util.CustomException
import com.example.moviedb.feature_movie_list.presentation.util.Resource
import java.net.UnknownHostException

class MockMovieRepository : IMovieRepository {

    private var shouldThrowNetworkError = false
    private var shouldThrowCustomException = false
    private var  customException : CustomException = CustomException.UnexpectedError()
    fun setShouldThrowNetworkError(value: Boolean) {
        shouldThrowNetworkError = value
    }
    fun setShouldThrowCustomException(value: Boolean) {
        shouldThrowCustomException = value
    }
    fun setCustomException(value:CustomException ){
        customException = value
    }


    override suspend fun getMovieList(pageNumber: Int): Resource<MovieList> {
        if (shouldThrowCustomException) throw customException
        if (shouldThrowNetworkError) throw UnknownHostException()
        return Resource.Success<MovieList>(MovieList())
    }

    override suspend fun getMovieById(movieId: Int): Resource<MovieDetails> {
        if (shouldThrowCustomException) throw customException
        if (shouldThrowNetworkError) throw UnknownHostException()

        return Resource.Success<MovieDetails>(MovieDetails())
    }
}