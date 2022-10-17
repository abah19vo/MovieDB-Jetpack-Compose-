package com.example.moviedb.feature_movie_list.presentation.movies

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviedb.feature_movie_list.domain.model.MovieList
import com.example.moviedb.feature_movie_list.domain.use_case.GetMovieList
import com.example.moviedb.feature_movie_list.presentation.util.Resource
import com.example.moviedb.MovieDBApp
import com.example.moviedb.feature_movie_list.domain.util.CustomException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel  @Inject constructor(
    app: Application,
    private val getMovieListUseCase: GetMovieList
) : AndroidViewModel(app) {

    val movieState: MutableStateFlow<Resource<MovieList>> = MutableStateFlow(Resource.Loading<MovieList>())
/*
*     val moviesResource = mutableStateOf(Resource.Loading<MovieList>())

*
* */
    init {
        getMovieList();

    }

    private fun getMovieList() = viewModelScope.launch {
        getMoviesCall()
    }

    private suspend fun getMoviesCall(){
        movieState.value= Resource.Loading()
        try {
            val response = getMovieListUseCase.invoke(connectivityManager())
            movieState.value=handleGetMovieListResponse(response)
        }catch (e:Throwable){
            when(e){
                is IOException -> movieState.value = Resource.Error(CustomException.NetworkFailure().data)
                is CustomException ->  movieState.value= Resource.Error(e.data)
                else -> movieState.value= Resource.Error(CustomException.UnexpectedError().data)
            }
        }
    }

    private fun handleGetMovieListResponse(response: Response<MovieList>): Resource<MovieList> {
        if(!response.isSuccessful) throw CustomException.UnSuccessfulRequest()
        else response.body()?.let { movieList->
            if(movieList.results.isEmpty()) throw CustomException.NoContent()
            return Resource.Success(movieList)
        }
        throw CustomException.UnexpectedError()
    }

    fun getImagePath() =

    private fun connectivityManager() : ConnectivityManager = getApplication<MovieDBApp>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

}