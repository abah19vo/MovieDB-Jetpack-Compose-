package com.example.moviedb.feature_movie_list.presentation.movies

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.moviedb.feature_movie_list.domain.model.MovieList
import com.example.moviedb.feature_movie_list.domain.use_case.GetMovieList
import com.example.moviedb.feature_movie_list.presentation.util.Resource
import com.example.moviedb.MovieApp
import com.example.moviedb.feature_movie_list.domain.util.InternetCheck

class MovieListViewModel(
    app: Application,
    val getMovieList: GetMovieList
) : AndroidViewModel(app) {

    val movies: MutableLiveData<Resource<MovieList>> = MutableLiveData()


    


    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<MovieApp>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        return InternetCheck.hasInternetConnection(connectivityManager)
    }
}