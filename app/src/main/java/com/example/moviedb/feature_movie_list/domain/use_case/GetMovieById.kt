package com.example.moviedb.feature_movie_list.domain.use_case

import com.example.moviedb.feature_movie_list.data.repository.IMovieRepository
import com.example.moviedb.feature_movie_list.domain.model.MovieDetails
import com.example.moviedb.feature_movie_list.domain.util.CustomException
import com.example.moviedb.feature_movie_list.presentation.util.Resource

class GetMovieById(private val repository: IMovieRepository) {
    suspend operator fun invoke(id: Int?): Resource<MovieDetails> {
        if(id == null) throw CustomException.UnexpectedNullValue()
        return repository.getMovieById(id)
    }
}