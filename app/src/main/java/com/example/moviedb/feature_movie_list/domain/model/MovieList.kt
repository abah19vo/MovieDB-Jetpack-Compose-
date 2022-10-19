package com.example.moviedb.feature_movie_list.domain.model

import com.google.gson.annotations.SerializedName

data class MovieList(

    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var results: ArrayList<Movie> = arrayListOf(),
    @SerializedName("total_results") var totalResults: Int? = null,
    @SerializedName("total_pages") var totalPages: Int? = null

)