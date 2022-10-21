package com.example.moviedb.feature_movie_list.presentation.movie_details

import com.example.moviedb.MovieDBApp
import com.example.moviedb.feature_movie_list.data.repository.MockMovieRepository
import com.example.moviedb.feature_movie_list.domain.model.MovieDetails
import com.example.moviedb.feature_movie_list.domain.use_case.GetMovieById
import com.example.moviedb.feature_movie_list.domain.util.CustomException
import com.example.moviedb.feature_movie_list.presentation.util.Resource
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class MovieDetailsViewModelUnitTest {

    private lateinit var viewModel: MovieDetailViewModel
    private val mockRepo:MockMovieRepository = MockMovieRepository()


    @Before
    fun setUp(){
        val useCase = GetMovieById(mockRepo)
        val app = MovieDBApp()
        viewModel = MovieDetailViewModel(
            getMovieByIdUseCase = useCase,
            app = app,
        )
    }

    @Test
    fun default_state_value_is_loading(){

        val movieState = viewModel.movieState
        assertThat(movieState.value).isInstanceOf(
            Resource.Loading<MovieDetails>().javaClass
        )
    }

    @Test
    fun get_movie_details_by_id_sets_the_state_to_success(){

        viewModel.getMovieDetails(123123)
        val movieState = viewModel.movieState
        assertThat(movieState.value).isInstanceOf(
            Resource.Success(MovieDetails()).javaClass
        )
    }

    @Test
    fun get_movie_details_by_empty_id_sets_the_state_to_Error(){


        viewModel.getMovieDetails(null)
        val movieState = viewModel.movieState
        val error = Resource.Error<MovieDetails>(CustomException.UnexpectedNullValue().data)
        assertThat(movieState.value).isInstanceOf(
            Resource.Error<MovieDetails>(CustomException.UnexpectedNullValue().data).javaClass
        )
        assertThat(movieState.value.message).isEqualTo(
            error.message
        )
    }

    @Test
    fun get_movie_details_by_id_sets_the_state_to_no_content_Error(){
        mockRepo.setShouldThrowCustomException(true)
        mockRepo.setCustomException(CustomException.NoContent())

        viewModel.getMovieDetails(123123)

        val movieState = viewModel.movieState
        val error = Resource.Error<MovieDetails>(CustomException.NoContent().data)
        assertThat(movieState.value).isInstanceOf(
            error.javaClass
        )
        assertThat(movieState.value.message).isEqualTo(
            error.message
        )
    }

    @Test
    fun get_movie_details_by_id_sets_the_state_to_unsuccessful_request_Error(){
        mockRepo.setShouldThrowCustomException(true)
        mockRepo.setCustomException(CustomException.UnSuccessfulRequest())

        viewModel.getMovieDetails(123123)
        val movieState = viewModel.movieState
        val error = Resource.Error<MovieDetails>(CustomException.UnSuccessfulRequest().data)
        assertThat(movieState.value).isInstanceOf(
            error.javaClass
        )
        assertThat(movieState.value.message).isEqualTo(
            error.message
        )
    }

    @Test
    fun get_movie_details_by_id_sets_the_state_to_network_Error(){
        mockRepo.setShouldThrowNetworkError(true)
        viewModel.getMovieDetails(123123)
        val movieState = viewModel.movieState
        val error = Resource.Error<MovieDetails>(CustomException.NetworkFailure().data)
        assertThat(movieState.value).isInstanceOf(
            error.javaClass
        )
        assertThat(movieState.value.message).isEqualTo(
            error.message
        )
    }
}

