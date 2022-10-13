package com.example.moviedb.feature_movie_list.data.api_services

import com.example.moviedb.feature_movie_list.data.util.Constants.apiKey
import com.example.moviedb.feature_movie_list.data.util.Constants.movieBaseUrl
import com.example.moviedb.feature_movie_list.domain.model.MovieDetails
import com.example.moviedb.feature_movie_list.domain.model.MovieList

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.Gson
import retrofit2.Retrofit
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.http.Query

interface MovieService {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String = apiKey
    ): Response<MovieDetails>

    @GET("movie/popular")
    suspend fun getMovieList(
        @Query("pageNumber") pageNumber: Int,
        @Query("api_key") key: String = apiKey
    ): Response<MovieList>
}


