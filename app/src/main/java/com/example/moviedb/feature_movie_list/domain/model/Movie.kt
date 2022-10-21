package com.example.moviedb.feature_movie_list.domain.model

import com.google.gson.annotations.SerializedName


data class Movie(

    @SerializedName("poster_path") var posterPath: String = "",
    @SerializedName("release_date") var releaseDate: String = "",
    @SerializedName("id") var id: Int = 0,
    @SerializedName("original_title") var originalTitle: String = "",
    @SerializedName("title") var title: String = "",
    @SerializedName("backdrop_path") var backdropPath: String = "",

)