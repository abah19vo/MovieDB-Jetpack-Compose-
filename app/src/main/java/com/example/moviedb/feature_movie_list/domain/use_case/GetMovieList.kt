package com.example.moviedb.feature_movie_list.domain.use_case

import android.net.ConnectivityManager
import com.example.moviedb.feature_movie_list.data.repository.MovieRepository
import com.example.moviedb.feature_movie_list.domain.model.MovieDetails
import com.example.moviedb.feature_movie_list.domain.model.MovieList
import com.example.moviedb.feature_movie_list.domain.util.InternetCheck
import retrofit2.Response

class GetMovieList(private val repository: MovieRepository) {

    suspend operator fun invoke(connectivityManager: ConnectivityManager): Response<MovieList> {
        if (InternetCheck.hasInternetConnection(connectivityManager))
            return repository.getMovieList();
        throw Exception("No Internet connection")
    }

}