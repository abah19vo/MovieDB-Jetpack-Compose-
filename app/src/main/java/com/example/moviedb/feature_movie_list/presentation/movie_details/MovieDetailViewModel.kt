package com.example.moviedb.feature_movie_list.presentation.movie_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedb.feature_movie_list.data.util.Constants
import com.example.moviedb.feature_movie_list.domain.model.MovieDetails
import com.example.moviedb.feature_movie_list.domain.use_case.GetMovieById
import com.example.moviedb.feature_movie_list.domain.util.CustomException
import com.example.moviedb.feature_movie_list.domain.util.ImageSize
import com.example.moviedb.feature_movie_list.presentation.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel  @Inject constructor(
    val app: Application,
    private val getMovieByIdUseCase: GetMovieById,
    ) : AndroidViewModel(app) {

    val movieState: MutableStateFlow<Resource<MovieDetails>> = MutableStateFlow(Resource.Loading())

    fun getImagePath(path: String?, size: ImageSize = ImageSize.LARGE): String =
        "${Constants.imageBaseUrl}${size.value}/$path"

    public fun refresh(movieId: Int?) {
        getMovieDetails(movieId)
    }

    fun getMovieDetails(movieId: Int?) = viewModelScope.launch {
        getMovieDetailsCall(movieId)
    }

    private suspend fun getMovieDetailsCall(movieId: Int?) {
        movieState.value = Resource.Loading()
        try {
            movieState.value = getMovieByIdUseCase.invoke(movieId)
        } catch (e: Throwable) {
            when (e) {
                is UnknownHostException ->movieState.value =  Resource.Error(CustomException.NetworkFailure().data)
                is CustomException -> movieState.value = Resource.Error(e.data)
                else -> movieState.value = Resource.Error(CustomException.UnexpectedError().data)
            }
        }
    }
}

