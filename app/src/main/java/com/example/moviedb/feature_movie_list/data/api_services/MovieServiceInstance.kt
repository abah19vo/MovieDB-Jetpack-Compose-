package com.example.moviedb.feature_movie_list.data.api_services

import com.example.moviedb.feature_movie_list.data.util.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieServiceInstance {

    companion object{
        private var gson: Gson = GsonBuilder().create()
        private val baseApiRetrofit by lazy<Retrofit>() {
            Retrofit.Builder()
                .baseUrl(Constants.movieBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        }
        val api: MovieService by lazy {
            baseApiRetrofit.create(MovieService::class.java)

        }
    }
}