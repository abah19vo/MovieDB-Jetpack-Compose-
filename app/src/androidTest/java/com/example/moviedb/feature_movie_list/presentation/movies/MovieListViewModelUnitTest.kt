package com.example.moviedb.feature_movie_list.presentation.movies

import com.example.moviedb.MovieDBApp
import com.example.moviedb.feature_movie_list.data.repository.MockMovieRepository
import com.example.moviedb.feature_movie_list.domain.model.MovieDetails
import com.example.moviedb.feature_movie_list.domain.use_case.GetMovieList
import com.example.moviedb.feature_movie_list.domain.util.CustomException
import com.example.moviedb.feature_movie_list.presentation.util.Resource
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class MovieListViewModelUnitTest {

    private lateinit var viewModel: MovieListViewModel
    private lateinit var mockRepo:MockMovieRepository


    @Before
    fun setUp(){
        mockRepo = MockMovieRepository()
        val useCase = GetMovieList(mockRepo)
        val app = MovieDBApp()
        viewModel = MovieListViewModel(
            getMovieListUseCase = useCase,
            app = app
        )
    }

    @Test
    fun get_movie_list_sets_the_state_to_success(){

        viewModel.refresh()
        val movieState = viewModel.movieState
        Truth.assertThat(movieState.value).isInstanceOf(
            Resource.Success<MovieDetails>(MovieDetails()).javaClass
        )
    }



    @Test
    fun get_movie_list_sets_the_state_to_no_content_Error(){
        mockRepo.setShouldThrowCustomException(true)
        mockRepo.setCustomException(CustomException.NoContent())


        viewModel.refresh()
        val movieState = viewModel.movieState
        val error = Resource.Error<MovieDetails>(CustomException.NoContent().data)
        Truth.assertThat(movieState.value).isInstanceOf(
            error.javaClass
        )
        Truth.assertThat(movieState.value.message).isEqualTo(
            error.message
        )
    }

    @Test
    fun get_movie_list_sets_the_state_to_unsuccessful_request_Error(){
        mockRepo.setShouldThrowCustomException(true)
        mockRepo.setCustomException(CustomException.UnSuccessfulRequest())

        viewModel.refresh()
        val movieState = viewModel.movieState
        val error = Resource.Error<MovieDetails>(CustomException.UnSuccessfulRequest().data)
        Truth.assertThat(movieState.value).isInstanceOf(
            error.javaClass
        )
        Truth.assertThat(movieState.value.message).isEqualTo(
            error.message
        )
    }

    @Test
    fun get_movie_list_sets_the_state_to_network_Error(){
        mockRepo.setShouldThrowNetworkError(true)

        viewModel.refresh()
        val movieState = viewModel.movieState
        val error = Resource.Error<MovieDetails>(CustomException.NetworkFailure().data)
        Truth.assertThat(movieState.value).isInstanceOf(
            error.javaClass
        )
        Truth.assertThat(movieState.value.message).isEqualTo(
            error.message
        )
    }
}
