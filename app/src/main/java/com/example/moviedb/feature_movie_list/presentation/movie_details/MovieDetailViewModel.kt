package com.example.moviedb.feature_movie_list.presentation.movie_details

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviedb.MovieDBApp
import com.example.moviedb.feature_movie_list.domain.model.MovieDetails
import com.example.moviedb.feature_movie_list.domain.use_case.GetMovieById
import com.example.moviedb.feature_movie_list.domain.util.CustomException
import com.example.moviedb.feature_movie_list.presentation.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel  @Inject constructor(
        app: Application,
        private val getMovieByIdUseCase: GetMovieById,
    ) : AndroidViewModel(app) {

    val movieResource: MutableLiveData<Resource<MovieDetails>> = MutableLiveData()

    init {
        getMovieList();
    }


    private fun getMovieList() = viewModelScope.launch {
        getMoviesCall()
    }

    private suspend fun getMoviesCall(){
        movieResource.postValue(Resource.Loading())
        try {
            val response = getMovieByIdUseCase.invoke(connectivityManager(), -1)
            movieResource.postValue(handleGetMovieDetailsResponse(response))
        }catch (e:Throwable){
            when(e){
                is IOException -> movieResource.postValue(Resource.Error(CustomException.NetworkFailure().data))
                is CustomException ->  movieResource.postValue( Resource.Error(e.data) )
                else -> movieResource.postValue(Resource.Error(CustomException.UnexpectedError().data))
            }
        }
    }

    private fun handleGetMovieDetailsResponse(response: Response<MovieDetails>): Resource<MovieDetails> {
        if(!response.isSuccessful) throw CustomException.UnSuccessfulRequest(response.message())
        if(response.body() == null) throw CustomException.NoContent()
        else response.body()?.let { movieList ->
            return Resource.Success(movieList)
        }
        throw CustomException.UnexpectedError()
    }


    private fun connectivityManager() : ConnectivityManager = getApplication<MovieDBApp>().getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager

}