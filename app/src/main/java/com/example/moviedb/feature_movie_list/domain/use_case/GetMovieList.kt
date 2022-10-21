package com.example.moviedb.feature_movie_list.domain.use_case

import com.example.moviedb.feature_movie_list.data.repository.IMovieRepository
import com.example.moviedb.feature_movie_list.domain.model.MovieList
import com.example.moviedb.feature_movie_list.presentation.util.Resource

class GetMovieList(private val repository: IMovieRepository) {
    suspend operator fun invoke(): Resource<MovieList> {
        return repository.getMovieList()
    }

}