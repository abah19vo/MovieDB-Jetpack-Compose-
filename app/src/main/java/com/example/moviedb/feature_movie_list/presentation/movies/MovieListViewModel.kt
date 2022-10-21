package com.example.moviedb.feature_movie_list.presentation.movies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedb.feature_movie_list.data.util.Constants.imageBaseUrl
import com.example.moviedb.feature_movie_list.domain.model.MovieList
import com.example.moviedb.feature_movie_list.domain.use_case.GetMovieList
import com.example.moviedb.feature_movie_list.domain.util.CustomException
import com.example.moviedb.feature_movie_list.domain.util.ImageSize
import com.example.moviedb.feature_movie_list.presentation.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel  @Inject constructor(
    app: Application,
    private val getMovieListUseCase: GetMovieList
) : AndroidViewModel(app) {
    val movieState: MutableStateFlow<Resource<MovieList>> = MutableStateFlow(Resource.Loading())

    init {
        getMovieList()
    }

    public fun refresh(){
        getMovieList()

    }

    fun getImagePath(path:String):String =  "$imageBaseUrl${ImageSize.LARGE.value}/$path"


    private fun getMovieList() = viewModelScope.launch {
        getMoviesCall()
    }

    private suspend fun getMoviesCall(){
        movieState.value= Resource.Loading()
        try {
            movieState.value = getMovieListUseCase.invoke()
        }catch (e:Throwable){
            when(e){
                is CustomException ->  movieState.value = Resource.Error(e.data)
                is UnknownHostException ->movieState.value =  Resource.Error(CustomException.NetworkFailure().data)
                else -> movieState.value = Resource.Error(CustomException.UnexpectedError().data)
            }
        }
    }



}