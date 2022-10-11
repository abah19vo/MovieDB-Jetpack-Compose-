package com.example.moviedb.feature_movie_list.domain.model


import com.google.gson.annotations.SerializedName

data class Genres(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null

)