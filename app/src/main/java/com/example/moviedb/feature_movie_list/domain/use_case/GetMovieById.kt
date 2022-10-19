package com.example.moviedb.feature_movie_list.domain.use_case

import android.net.ConnectivityManager
import com.example.moviedb.feature_movie_list.data.repository.MovieRepository
import com.example.moviedb.feature_movie_list.domain.model.MovieDetails
import com.example.moviedb.feature_movie_list.domain.util.CustomException
import com.example.moviedb.feature_movie_list.domain.util.InternetCheck
import retrofit2.Response

class GetMovieById(private val repository: MovieRepository) {

    suspend operator fun invoke(connectivityManager: ConnectivityManager, id: Int?): Response<MovieDetails> {
        if(id == null) throw CustomException.UnexpectedNullValue()
        if (InternetCheck.hasInternetConnection(connectivityManager))
            return repository.getMovieById(id)
        throw CustomException.NetworkFailure()
    }
}